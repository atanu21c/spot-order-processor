package com.change.spot.order.entity.wallet

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class UserBalance(
    @JsonProperty("id")
    var walletId: Long,
    var currency: String,
    var balance: BigDecimal,
    var totalBalance: BigDecimal,
    var active: Boolean,
    var countTransactions: Long
)
