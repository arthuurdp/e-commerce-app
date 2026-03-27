package com.ecommerce.app.data.model.product

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
    val weight: Double,
    val width: Int,
    val height: Int,
    val length: Int,
    val images: List<String>,
    val mainImageId: Long? = null,
    val categoryIds: List<Long>
)