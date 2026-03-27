package com.ecommerce.app.data.model.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val mainImage: String?
) : Parcelable