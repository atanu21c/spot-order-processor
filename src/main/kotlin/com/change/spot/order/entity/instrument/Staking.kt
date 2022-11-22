package com.change.spot.order.entity.instrument

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Staking(
    var enabled: Boolean?,
    var rewardRate: BigDecimal = BigDecimal.ZERO,
    var soldOut: Boolean?
) : Serializable
