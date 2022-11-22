package com.change.spot.order.entity.order

import java.math.BigDecimal
import java.time.Instant

data class OrderTrade(
    var tradeId: Long,
    var orderId: Long,
    var externalOrderId: String?,
    var pair: String,
    var price: BigDecimal,
    var volume: BigDecimal,
    var cost: BigDecimal? = BigDecimal.ZERO,
    var executedAt: Instant? = null
)
