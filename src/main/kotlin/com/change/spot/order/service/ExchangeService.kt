package com.change.spot.order.service

import com.change.spot.order.dto.ExchangeOrder

/**
 * @author atanu
 */
interface ExchangeService {
    fun createOrder(exchangeOrder: ExchangeOrder)
}