package com.change.spot.order.service.impl

import com.change.spot.order.entity.instrument.Instrument
import com.change.spot.order.entity.order.Order
import com.change.spot.order.service.OrderProcessor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
@Qualifier("marketOrderProcessor")
class MarketOrderProcessorImpl : OrderProcessor {
    override fun createOrder(order: Order, orderSize: BigDecimal) {
        TODO("Not yet implemented")
    }
}