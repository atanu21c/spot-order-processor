package com.change.spot.order.service.impl

import com.change.spot.order.dto.ExchangeOrder
import com.change.spot.order.entity.order.Exchange
import com.change.spot.order.entity.order.Order
import com.change.spot.order.entity.order.OrderEvent
import com.change.spot.order.entity.order.Side
import com.change.spot.order.service.OrderProcessor
import com.change.spot.order.service.events.OrderEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
@Qualifier("limitOrderProcessor")
class LimitOrderProcessorImpl(
    @Qualifier("krakenExchange") private val krakenExchange: KrakenExchangeServiceImpl,
    private val applicationEventPublisher: ApplicationEventPublisher
) : OrderProcessor {

    private val logger: Logger = LoggerFactory.getLogger(LimitOrderProcessorImpl::class.java)

    /**
     * Create and save lIMIT Order
     */
    @Async
    override fun createOrder(order: Order, orderSize: BigDecimal) {
        try {
            calculateAmount(order, orderSize)
            // Hedge Limit Order on KRAKEN
            order.exchange = Exchange.KRAKEN

            logger.info("Inside processor : {}", order)
            applicationEventPublisher.publishEvent(
                OrderEvents(
                    this,
                    OrderEvent.CREATE,
                    order
                )
            )

            // Convert Order to ExchangeOrder
            val exchangeOrder = ExchangeOrder(
                internalOrderId = order.orderId,
                internalOrderReferenceId = order.orderReferenceId,
                side = order.side,
                type = order.type,
                pair = order.pair,
                requestedPrice = order.price,
                orderEvent = OrderEvent.CREATE,
                volume = order.requetedVolume!!,
                exchange = order.exchange
            )

            krakenExchange.createOrder(exchangeOrder)
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }

    private fun calculateAmount(order: Order, requestedSize: BigDecimal) {
        if (order.side == Side.BUY) {
            if (order.isFiatUsed) {
                order.toAmount = requestedSize.divide(order.price, 8, RoundingMode.HALF_UP)
                order.fromAmount = requestedSize
                order.requetedVolume = order.toAmount
            } else {
                order.toAmount = requestedSize
                order.fromAmount = order.price!!.multiply(requestedSize)
                order.requetedVolume = requestedSize
            }
        } else {
            if (order.isFiatUsed) {
                order.toAmount = requestedSize
                order.fromAmount = requestedSize.divide(order.price, 8, RoundingMode.HALF_UP)
                order.requetedVolume = order.fromAmount
            } else {
                order.toAmount = order.price!!.multiply(requestedSize)
                order.fromAmount = requestedSize
                order.requetedVolume = requestedSize
            }
        }
    }
}