package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.product.*
import com.ecommerce.app.data.model.util.PageResponse
import retrofit2.Response
import retrofit2.http.*

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("name") name: String? = null,
        @Query("categoryIds") categoryIds: List<Long>? = null
    ): Response<PageResponse<ProductResponse>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<ProductDetailsResponse>

    @POST("products")
    suspend fun createProduct(@Body request: CreateProductRequest): Response<ProductDetailsResponse>

    @PATCH("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body request: UpdateProductRequest
    ): Response<ProductDetailsResponse>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Long): Response<Unit>

    @PATCH("products/{id}/main-image/{mainImageId}")
    suspend fun setMainImage(
        @Path("id") id: Long,
        @Path("mainImageId") mainImageId: Long
    ): Response<ProductDetailsResponse>
}