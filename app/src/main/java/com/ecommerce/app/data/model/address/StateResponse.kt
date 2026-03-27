package com.ecommerce.app.data.model.address

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StateResponse(
    val id: Long,
    val name: String,
    val uf: String
) : Parcelable