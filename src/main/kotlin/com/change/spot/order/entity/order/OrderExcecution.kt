package com.change.spot.order.entity.order

import java.math.BigDecimal
import java.time.Instant

data class OrderExcecution(
    var executionId: Long,
    var orderId: Long,
    var executedVolume: BigDecimal?,
    var averagePrice: BigDecimal?,
    var totalFee: BigDecimal?,
    var cost: BigDecimal?,
    var executionStatus: ExceutionStatus,
    val openedAt: Instant = Instant.now(),
    val closedAt: Instant? = null
)
