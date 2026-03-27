package com.ecommerce.app.data.model.address

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressResponse(
    val id: Long,
    val name: String,
    val street: String,
    val number: Int,
    val complement: String?,
    val neighborhood: String,
    val postalCode: String = "",
    val city: CityResponse,
    val state: StateResponse
) : Parcelable