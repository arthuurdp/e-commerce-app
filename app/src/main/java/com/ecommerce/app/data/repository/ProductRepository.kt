package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.ProductApiService
import com.ecommerce.app.data.model.product.CreateProductRequest
import com.ecommerce.app.data.model.product.HomeProductsResponse
import com.ecommerce.app.data.model.product.ProductDetailsResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.product.UpdateProductRequest
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class ProductRepository @Inject constructor(private val api: ProductApiService) : BaseRepository() {

    suspend fun getProducts(
        page: Int = 0,
        size: Int = 10,
        name: String? = null,
        categoryIds: List<Long>? = null
    ): NetworkResult<PageResponse<ProductResponse>> =
        safeApiCall { api.getProducts(page, size, name, categoryIds) }

    suspend fun getHomeProducts(productsPerCategory: Int = 10): NetworkResult<List<HomeProductsResponse>> =
        safeApiCall { api.getHomeProducts(productsPerCategory) }

    suspend fun getProductById(id: Long): NetworkResult<ProductDetailsResponse> =
        safeApiCall { api.getProductById(id) }
}