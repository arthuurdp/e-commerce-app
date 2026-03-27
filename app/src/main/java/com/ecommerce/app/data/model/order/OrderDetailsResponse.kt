package com.ecommerce.app.data.model.order

data class OrderDetailsResponse(
    val id: Long,
    val status: String,
    val total: Double,
    val createdAt: String,
    val items: List<OrderItemResponse>
)