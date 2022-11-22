package com.change.spot.order.service

import com.change.spot.order.entity.order.Order
import java.math.BigDecimal

interface OrderProcessor {
    fun createOrder(order: Order, orderSize: BigDecimal)
}