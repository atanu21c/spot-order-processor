package com.change.spot.order.service

import com.change.spot.order.dto.OrderResponse
import com.change.spot.order.entity.order.OrderRequest

interface OrderService {
    fun createLimitOrder(order: OrderRequest): OrderResponse
}