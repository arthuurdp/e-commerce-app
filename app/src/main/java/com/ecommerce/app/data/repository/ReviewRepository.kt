package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.ReviewApiService
import com.ecommerce.app.data.model.review.ReviewRequest
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewApiService: ReviewApiService
) : BaseRepository() {

    suspend fun createReview(reviewRequest: ReviewRequest): NetworkResult<ReviewResponse> {
        return safeApiCall { reviewApiService.createReview(reviewRequest) }
    }

    suspend fun getProductReviews(productId: Long): NetworkResult<List<ReviewResponse>> {
        return safeApiCall { reviewApiService.getProductReviews(productId) }
    }

    suspend fun getAverageRating(productId: Long): NetworkResult<Double> {
        return safeApiCall { reviewApiService.getAverageRating(productId) }
    }
}
