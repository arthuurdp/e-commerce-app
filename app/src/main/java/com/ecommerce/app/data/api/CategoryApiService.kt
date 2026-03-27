package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.category.*
import com.ecommerce.app.data.model.util.PageResponse
import retrofit2.Response
import retrofit2.http.*

interface CategoryApiService {

    @GET("categories")
    suspend fun getCategories(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 50
    ): Response<PageResponse<CategoryResponse>>

    @GET("categories/{id}")
    suspend fun getCategoryById(@Path("id") id: Long): Response<CategoryResponse>

    @POST("categories")
    suspend fun createCategory(@Body request: CreateCategoryRequest): Response<CategoryResponse>

    @PATCH("categories/{id}")
    suspend fun updateCategory(
        @Path("id") id: Long,
        @Body request: UpdateCategoryRequest
    ): Response<CategoryResponse>

    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Long): Response<Unit>
}