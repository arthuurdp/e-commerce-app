package com.ecommerce.app.data.model.order

data class CheckoutRequest(
    val addressId: Long,
    val paymentMethod: String
)