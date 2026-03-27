package com.ecommerce.app.data.model.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductImageResponse(
    val id: Long,
    val url: String,
    val mainImage: Boolean
) : Parcelable