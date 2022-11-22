package com.change.spot.order.client

import com.change.spot.order.entity.wallet.UserBalance
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WalletClient(private val walletWebClient: WebClient) {

    fun getUserBalance(userId: String): List<UserBalance?>? {
        return walletWebClient.get()
            .uri { builder ->
                builder.path("/internal/wallets/with-balances/{userId}")
                    .build(userId)
            }
            .retrieve()
            // .bodyToMono(Instrument::class.java)
            .bodyToMono(object : ParameterizedTypeReference<List<UserBalance?>?>() {})
            .block()
    }
}