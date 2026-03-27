package com.ecommerce.app.data.model.address

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityResponse(
    val id: Long,
    val name: String
) : Parcelable