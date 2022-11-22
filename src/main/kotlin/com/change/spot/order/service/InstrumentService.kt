package com.change.spot.order.service

import com.change.spot.order.client.MarketDataClient
import com.change.spot.order.entity.instrument.Instrument
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class InstrumentService(private val marketdataClient: MarketDataClient) {

    private val logger: Logger = LoggerFactory.getLogger(InstrumentService::class.java)

    @Cacheable("instrumentCache")
    fun getInstruments(): List<Instrument?>? {
        logger.info("Get all instruments details !!")
        // "Can also aggregate instrument configuration with fees")
        return marketdataClient.getInstruments();
    }

    /**
     * Cleared by call from a webhook
     */
    @CacheEvict(value = ["instrumentCache"], allEntries = true)
    fun evictAllInstruments() {
        logger.info("Clearing all instruments from cache")
    }
}