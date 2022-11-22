package com.change.spot.order.controller

import com.change.spot.order.dto.OrderResponse
import com.change.spot.order.entity.order.OrderRequest
import com.change.spot.order.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/spot/order")
@Validated
class OrderController(val orderService: OrderService) {

    @PostMapping("/limit")
    @Operation(
        summary = "Create LIMIT/STOP order"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Order Created with reference", content = [
                    (Content(mediaType = "application/json"))]
            ),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Not Found", content = [Content()])]
    )
    fun createLimitOrder(@Valid @RequestBody order: OrderRequest): ResponseEntity<OrderResponse> {
        return ResponseEntity.ok(orderService.createLimitOrder(order));
    }
}