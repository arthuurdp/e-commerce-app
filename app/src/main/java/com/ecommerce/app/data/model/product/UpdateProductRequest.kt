package com.ecommerce.app.data.model.product

data class UpdateProductRequest(
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val stock: Int? = null,
    val weight: Double? = null,
    val width: Int? = null,
    val height: Int? = null,
    val length: Int? = null,
    val mainImageId: Long? = null,
    val imageUrls: List<String>? = null,
    val categoryIds: List<Long>? = null
)