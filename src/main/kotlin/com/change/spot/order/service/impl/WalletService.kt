package com.change.spot.order.service.impl

import com.change.spot.order.client.WalletClient
import com.change.spot.order.entity.wallet.UserBalance
import com.change.spot.order.exception.RestAPIException
import org.springframework.stereotype.Service

@Service
class WalletService(private val walletClient: WalletClient) {

    fun getUserBalance(userId: String): List<UserBalance?>? {
        try {
            return walletClient.getUserBalance(userId)
        } catch (e: Exception) {
            throw RestAPIException("Error in calling wallet api")
        }

    }
}