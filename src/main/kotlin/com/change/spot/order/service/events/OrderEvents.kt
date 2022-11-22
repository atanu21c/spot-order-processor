package com.change.spot.order.service.events

import com.change.spot.order.entity.order.Order
import org.springframework.context.ApplicationEvent

class OrderEvents(
    source: Any,
    val orderEvent: com.change.spot.order.entity.order.OrderEvent,
    val order: Order
) : ApplicationEvent(source)