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
}