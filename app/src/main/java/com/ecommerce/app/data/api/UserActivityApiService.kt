package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.review.ReviewResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET

interface UserActivityApiService {
    @GET("users/me/activity")
    suspend fun getRecentActivity(): Response<List<NotificationResponse>>

    @DELETE("users/me/activity")
    suspend fun clearRecentActivity(): Response<Unit>

    @GET("users/me/activity/reviews")
    suspend fun getMyReviews(): Response<List<ReviewResponse>>

    @GET("users/me/activity/comments")
    suspend fun getMyComments(): Response<List<CommentResponse>>

    @GET("users/me/activity/favorites")
    suspend fun getMyFavorites(): Response<Set<ProductResponse>>
}
