package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.FavoriteApiService
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteApiService: FavoriteApiService
) : BaseRepository() {

    suspend fun addFavorite(productId: Long): NetworkResult<Unit> {
        return safeApiCall { favoriteApiService.addFavorite(productId) }
    }

    suspend fun removeFavorite(productId: Long): NetworkResult<Unit> {
        return safeApiCall { favoriteApiService.removeFavorite(productId) }
    }

    suspend fun getUserFavorites(): NetworkResult<Set<ProductResponse>> {
        return safeApiCall { favoriteApiService.getUserFavorites() }
    }
}
