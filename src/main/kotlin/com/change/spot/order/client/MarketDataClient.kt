package com.change.spot.order.client

import com.change.spot.order.entity.instrument.Instrument
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class MarketDataClient(private val marketdataWebClient: WebClient) {

    fun getInstruments(): List<Instrument?>? {
        return marketdataWebClient.get()
            .uri { builder ->
                builder.path("/internal/instruments")
                    .queryParam("type", "CRYPTO")
                    .queryParam("enabledOnly", true)
                    .build()
            }
            .retrieve()
           // .bodyToMono(Instrument::class.java)
            .bodyToMono(object : ParameterizedTypeReference<List<Instrument?>?>() {})
            .block()
    }
}