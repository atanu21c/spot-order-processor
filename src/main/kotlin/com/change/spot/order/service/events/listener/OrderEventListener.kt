package com.change.spot.order.service.events.listener

import com.change.spot.order.entity.order.Order
import com.change.spot.order.entity.order.OrderEvent
import com.change.spot.order.exception.InsufficientBalanceException
import com.change.spot.order.repository.OrderRepository
import com.change.spot.order.service.events.OrderEvents
import com.change.spot.order.service.impl.WalletService
import com.change.spot.order.util.OrderUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderEventListener(
    private val orderRepository: OrderRepository,
    private val walletService: WalletService
) {

    private val logger: Logger = LoggerFactory.getLogger(OrderEventListener::class.java)

    @EventListener
    fun handleEvent(event: OrderEvents) {
        logger.info("Received {} Order Event for orderReferenceId : {}", event.orderEvent, event.order.orderReferenceId)
        when (event.orderEvent) {
            OrderEvent.CREATE -> onOrderCreate(event.order)
            else -> {
                logger.error("Invalid Order event")
            }
        }
    }

    private fun onOrderCreate(order: Order) {
        val userBalance = walletService.getUserBalance(order.userId)
        // Check for sufficient user balance
        val walletBalance = if (OrderUtils.checkEUR(order.fromSymbol))
            userBalance!!.filter { it!!.currency == "EURT" }!!.firstOrNull()
        else
            userBalance!!.filter { it!!.currency == order.fromSymbol }!!.firstOrNull()
        if (walletBalance != null && order.requetedVolume!! < walletBalance.balance) {
            throw InsufficientBalanceException("User does not have sufficient balance ")
        }
        orderRepository.save(order)
    }
}