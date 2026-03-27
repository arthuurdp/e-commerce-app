package com.ecommerce.app.data.model.address

data class CepLookupResponse(
    val cep: String,
    val street: String?,
    val neighborhood: String?,
    val cityId: Long,
    val cityName: String,
    val stateId: Long,
    val stateName: String,
    val stateUf: String
)