package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.UserActivityApiService
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class UserActivityRepository @Inject constructor(
    private val userActivityApiService: UserActivityApiService
) : BaseRepository() {

    suspend fun getRecentActivity(): NetworkResult<List<NotificationResponse>> {
        return safeApiCall { userActivityApiService.getRecentActivity() }
    }

    suspend fun clearRecentActivity(): NetworkResult<Unit> {
        return safeApiCall { userActivityApiService.clearRecentActivity() }
    }

    suspend fun getMyReviews(): NetworkResult<List<ReviewResponse>> {
        return safeApiCall { userActivityApiService.getMyReviews() }
    }

    suspend fun getMyComments(): NetworkResult<List<CommentResponse>> {
        return safeApiCall { userActivityApiService.getMyComments() }
    }

    suspend fun getMyFavorites(): NetworkResult<Set<ProductResponse>> {
        return safeApiCall { userActivityApiService.getMyFavorites() }
    }
}
