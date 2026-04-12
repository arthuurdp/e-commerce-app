package com.ecommerce.app.data.model.product

import com.ecommerce.app.data.model.category.CategoryResponse

data class HomeProductsResponse(
    val category: CategoryResponse,
    val products: List<ProductResponse>
)