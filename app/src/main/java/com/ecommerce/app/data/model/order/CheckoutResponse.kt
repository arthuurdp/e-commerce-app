package com.ecommerce.app.data.model.order

data class CheckoutResponse(
    val orderId: Long,
    val sessionId: String,
    val checkoutUrl: String
)