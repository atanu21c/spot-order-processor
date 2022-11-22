package com.change.spot.order.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration {

    @Value("\${clients.marketdata.url}")
    private lateinit var marketdataURL: String

    @Value("\${clients.wallet.url}")
    private lateinit var walletURL: String

    @Bean
    fun marketdataWebClient(builder: WebClient.Builder): WebClient =
        builder
            .baseUrl(marketdataURL)
            .build()

    @Bean
    fun walletWebClient(builder: WebClient.Builder): WebClient =
        builder
            .baseUrl(walletURL)
            // .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
            .build()
}