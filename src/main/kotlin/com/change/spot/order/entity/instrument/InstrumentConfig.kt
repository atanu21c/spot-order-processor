package com.change.spot.order.entity.instrument

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class InstrumentConfig(
    var instrumentDetails: String?,
    var staking: Staking?
) : Serializable
