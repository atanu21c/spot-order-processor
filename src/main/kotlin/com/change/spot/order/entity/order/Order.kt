package com.change.spot.order.entity.order

import java.math.BigDecimal
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderId: Long,
    @Column(name = "user_id")
    var userId: String,
    @Column(name = "from_symbol")
    var fromSymbol: String,
    @Column(name = "to_symbol")
    var toSymbol: String,
    var pair: String,
    @Column(name = "from_amount")
    var fromAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(name = "to_amount")
    var toAmount: BigDecimal? = BigDecimal.ZERO,
    @Enumerated(EnumType.STRING)
    var side: Side,
    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    var type: OrderType,
    var price: BigDecimal?,
    @Column(name = "requested_volume")
    var requetedVolume: BigDecimal? = BigDecimal.ZERO,
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,
    @Column(name = "order_ref_id")
    var orderReferenceId: UUID,
    var fee: BigDecimal? = BigDecimal.ZERO,
    var exchange: Exchange? = Exchange.KRAKEN,
    @Column(name = "fiat_used")
    var isFiatUsed: Boolean,
    @Column(name = "created_at")
    val createdAt: Instant = Instant.now(),
    @Column(name = "created_by")
    var createdBy: String? = null,
    @Column(name = "updated_at")
    val updatedAt: Instant? = null
)