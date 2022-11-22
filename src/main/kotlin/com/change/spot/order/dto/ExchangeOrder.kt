package com.change.spot.order.dto

import com.change.spot.order.entity.order.Exchange
import com.change.spot.order.entity.order.OrderEvent
import com.change.spot.order.entity.order.OrderType
import com.change.spot.order.entity.order.Side
import java.math.BigDecimal
import java.util.*

data class ExchangeOrder(
    var internalOrderId: Long,
    var internalOrderReferenceId: UUID,
    var side: Side,
    var type: OrderType,
    var pair: String,
    var requestedPrice: BigDecimal?,
    var orderEvent: OrderEvent?,
    var volume: BigDecimal,
    var exchange: Exchange? = Exchange.KRAKEN
)
