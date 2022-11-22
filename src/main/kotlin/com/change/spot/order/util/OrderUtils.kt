package com.change.spot.order.util

import com.change.spot.order.entity.order.Exchange
import com.change.spot.order.exception.InvalidOrderException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Commonly used Order utility methods.
 */
object OrderUtils {
    private val logger: Logger = LoggerFactory.getLogger(OrderUtils::class.java)

    const val SYMBOL_EUR = "EUR"
    const val SYMBOL_EURT = "EURT"

    /**
     * Get exchange for order execution
     * @param executionType from instrument
     * @return Exchange
     */
    fun getExchange(executionType: String): Exchange {
        when (executionType) {
            "PRIMEXM" -> return Exchange.PRIMEXM
            "INTERNAL" -> return Exchange.KRAKEN
            else -> {
                logger.error("Invalid execution type")
                throw InvalidOrderException("Invalid execution type")
            }
        }
    }

    fun checkEUR(symbol: String): Boolean {
        return symbol == SYMBOL_EUR || symbol == SYMBOL_EURT
    }
}