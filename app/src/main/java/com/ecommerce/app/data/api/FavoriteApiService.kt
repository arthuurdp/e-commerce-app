package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.product.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface FavoriteApiService {
    @POST("users/me/favorites/{productId}")
    suspend fun addFavorite(
        @Path("productId") productId: Long
    ): Response<Unit>

    @DELETE("users/me/favorites/{productId}")
    suspend fun removeFavorite(
        @Path("productId") productId: Long
    ): Response<Unit>

    @GET("users/me/favorites")
    suspend fun getUserFavorites(): Response<Set<ProductResponse>>
}
