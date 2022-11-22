package com.change.spot.order.service.impl

import com.change.spot.order.dto.ExchangeOrder
import com.change.spot.order.service.ExchangeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
@Qualifier("krakenExchange")
class KrakenExchangeServiceImpl(
    private val kafkaTemplate: KafkaTemplate<String, ExchangeOrder>
) : ExchangeService {

    private val logger: Logger = LoggerFactory.getLogger(KrakenExchangeServiceImpl::class.java)

    @Value(value = "\${kraken.topic.name}")
    private val krakenTopic: String? = null

    override fun createOrder(exchangeOrder: ExchangeOrder) {
        logger.info("Create order request on KRAKEN exchange {}", exchangeOrder)
        sendMessage(exchangeOrder)
    }


    fun sendMessage(exchangeOrder: ExchangeOrder) {
        kafkaTemplate.send(krakenTopic!!, exchangeOrder.internalOrderReferenceId.toString(), exchangeOrder)
            .addCallback({
                logger.info(
                    "Sent Order to KRAKEN topic=[internalOrderId : {},  internalOrderReferenceId : {} ] with offset=[{}]",
                    exchangeOrder.internalOrderId,
                    exchangeOrder.internalOrderReferenceId,
                    it!!.recordMetadata.offset()
                )
            }, {
                logger.error(
                    "Unable to send Order=["
                            + exchangeOrder.internalOrderId + "] due to : " + it.message
                )
            })
    }
}