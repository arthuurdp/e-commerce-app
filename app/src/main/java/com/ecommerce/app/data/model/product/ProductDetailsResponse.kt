package com.ecommerce.app.data.model.product

import android.os.Parcelable
import com.ecommerce.app.data.model.category.CategoryResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailsResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imgs: List<ProductImageResponse>,
    val weight: Double,
    val width: Int,
    val height: Int,
    val length: Int,
    val categories: List<CategoryResponse>
) : Parcelable