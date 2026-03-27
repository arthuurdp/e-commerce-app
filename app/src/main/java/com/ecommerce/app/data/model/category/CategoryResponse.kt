package com.ecommerce.app.data.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryResponse(
    val id: Long,
    val name: String
) : Parcelable