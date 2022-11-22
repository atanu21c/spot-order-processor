package com.change.spot.order.service.impl

import com.change.spot.order.dto.ExchangeOrder
import com.change.spot.order.service.ExchangeService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("primexmExchange")
class PrimexmExchangeServiceImpl : ExchangeService {
    override fun createOrder(exchangeOrder: ExchangeOrder) {
        TODO("Not yet implemented")
    }
}