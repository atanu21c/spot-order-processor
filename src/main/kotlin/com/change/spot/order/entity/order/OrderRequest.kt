package com.change.spot.order.entity.order

import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class OrderRequest(
    @field:Valid @field:NotNull @field:NotEmpty(message = "symbol can not be empty") var symbol: String,
    @field:NotNull var side: Side,
    @field:NotNull var type: OrderType,
    @field:NotNull(message = "size can not be empty") var size: BigDecimal,
    var price: BigDecimal?,
    var isFiatUsed: Boolean? = false,
    var sizeIndicator: Int?
)
