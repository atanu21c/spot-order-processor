package com.change.spot.order.service.impl

import com.change.spot.order.dto.OrderResponse
import com.change.spot.order.entity.instrument.Instrument
import com.change.spot.order.entity.order.*
import com.change.spot.order.exception.InvalidOrderException
import com.change.spot.order.service.InstrumentService
import com.change.spot.order.service.OrderProcessor
import com.change.spot.order.service.OrderService
import com.change.spot.order.util.OrderUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class OrderServiceImpl(
    private val instrumentService: InstrumentService,
    @Qualifier("limitOrderProcessor") private val limitOrderProcessor: OrderProcessor
) : OrderService {

    private val logger: Logger = LoggerFactory.getLogger(OrderServiceImpl::class.java)

    override fun createLimitOrder(order: OrderRequest): OrderResponse {
        // Validate limit order
        validateLimitOrder(order)
        val instrumentDetail = validateSymbol(order.symbol)
        logger.info("Received {} Order : {}", order.type, order)
        val orderToProcess =
            Order(
                userId = "4428",
                orderId = 0,
                fromSymbol = if (order.side == Side.BUY) instrumentDetail.toAssetId else instrumentDetail.fromAssetId,
                toSymbol = if (order.side == Side.BUY) instrumentDetail.fromAssetId else instrumentDetail.toAssetId,
                side = order.side,
                type = order.type,
                price = order.price,
                orderStatus = OrderStatus.PENDING,
                orderReferenceId = UUID.randomUUID(),
                isFiatUsed = order.isFiatUsed!!,
                exchange = OrderUtils.getExchange(instrumentDetail.executionType),
                pair = order.symbol,
                createdBy = "userId"
            )
        limitOrderProcessor.createOrder(orderToProcess, order.size)
        return OrderResponse(orderToProcess.orderReferenceId)
    }

    private fun validateLimitOrder(order: OrderRequest) {
        if (order.type != OrderType.LIMIT)
            throw InvalidOrderException("Invalid Order type")
        if (order.price == null || order.price!!.compareTo(BigDecimal.ZERO) == 0)
            throw InvalidOrderException("Price is required for LIMIT order")

    }

    private fun validateSymbol(symbol: String): Instrument {
        // add a check for valid symbol
        return getInstrument(symbol) ?: throw InvalidOrderException("Invalid Order Symbol")
    }

    private fun getInstrument(symbol: String): Instrument? {
        return instrumentService.getInstruments()?.filter { it!!.name == symbol }!!.firstOrNull()
    }
}