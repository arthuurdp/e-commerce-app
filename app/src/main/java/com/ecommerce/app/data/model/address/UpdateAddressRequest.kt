package com.ecommerce.app.data.model.address

data class UpdateAddressRequest(
    val name: String? = null,
    val street: String? = null,
    val number: Int? = null,
    val complement: String? = null,
    val neighborhood: String? = null,
    val postalCode: String? = null
)