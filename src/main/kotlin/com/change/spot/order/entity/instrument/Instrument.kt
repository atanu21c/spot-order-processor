package com.change.spot.order.entity.instrument

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Instrument(
    var id: String,
    var name: String,
    var fromAssetId: String,
    var toAssetId: String,
    var provider: String,
    var providerId: String,
    var type: String,
    var executionType: String,
    var enabled: Boolean,
    var jsonConfig: String,
    var updatedAt: String?,
    var updatedBy: String?,
    var cacheKey: String?,
    var assetPair: String,
    var instrumentConfig: InstrumentConfig?
) : Serializable
