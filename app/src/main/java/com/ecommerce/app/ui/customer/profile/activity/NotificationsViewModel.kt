package com.ecommerce.app.ui.customer.profile.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.review.AddCommentToReviewRequest
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.data.model.review.UpdateReviewRequest
import com.ecommerce.app.data.repository.CommentRepository
import com.ecommerce.app.data.repository.FavoriteRepository
import com.ecommerce.app.data.repository.ReviewRepository
import com.ecommerce.app.data.repository.UserActivityRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val userActivityRepository: UserActivityRepository,
    private val favoriteRepository: FavoriteRepository,
    private val reviewRepository: ReviewRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {

    private val _recentActivity = MutableLiveData<NetworkResult<List<NotificationResponse>>>()
    val recentActivity: LiveData<NetworkResult<List<NotificationResponse>>> = _recentActivity

    private val _myReviews = MutableLiveData<NetworkResult<List<ReviewResponse>>>()
    val myReviews: LiveData<NetworkResult<List<ReviewResponse>>> = _myReviews

    private val _myComments = MutableLiveData<NetworkResult<List<CommentResponse>>>()
    val myComments: LiveData<NetworkResult<List<CommentResponse>>> = _myComments

    private val _myFavorites = MutableLiveData<NetworkResult<Set<ProductResponse>>>()
    val myFavorites: LiveData<NetworkResult<Set<ProductResponse>>> = _myFavorites

    private val _removeFavoriteResult = MutableLiveData<NetworkResult<Unit>>()
    val removeFavoriteResult: LiveData<NetworkResult<Unit>> = _removeFavoriteResult

    private val _editReviewResult = MutableLiveData<NetworkResult<Unit>?>()
    val editReviewResult: LiveData<NetworkResult<Unit>?> = _editReviewResult

    private val _deleteReviewResult = MutableLiveData<NetworkResult<Unit>?>()
    val deleteReviewResult: LiveData<NetworkResult<Unit>?> = _deleteReviewResult

    private val _clearActivityState = MutableLiveData<NetworkResult<Unit>?>()
    val clearActivityState: LiveData<NetworkResult<Unit>?> = _clearActivityState

    init {
        loadRecentActivity()
    }

    fun loadRecentActivity() {
        viewModelScope.launch {
            _recentActivity.value = NetworkResult.Loading
            _recentActivity.value = userActivityRepository.getRecentActivity()
        }
    }

    fun clearRecentActivity() {
        viewModelScope.launch {
            _clearActivityState.value = NetworkResult.Loading
            val result = userActivityRepository.clearRecentActivity()
            _clearActivityState.value = result
            if (result is NetworkResult.Success) {
                loadRecentActivity()
            }
        }
    }

    fun resetClearActivityState() {
        _clearActivityState.value = null
    }

    fun loadMyReviews() {
        viewModelScope.launch {
            _myReviews.value = NetworkResult.Loading
            _myReviews.value = userActivityRepository.getMyReviews()
        }
    }

    fun loadMyComments() {
        viewModelScope.launch {
            _myComments.value = NetworkResult.Loading
            _myComments.value = userActivityRepository.getMyComments()
        }
    }

    fun loadMyFavorites() {
        viewModelScope.launch {
            _myFavorites.value = NetworkResult.Loading
            _myFavorites.value = userActivityRepository.getMyFavorites()
        }
    }

    fun editReview(review: ReviewResponse, rating: Int, commentContent: String) {
        viewModelScope.launch {
            _editReviewResult.value = NetworkResult.Loading

            val normalizedComment = commentContent.trim()
            val currentComment = review.comment?.content?.trim().orEmpty()
            val ratingChanged = rating != review.rating
            val commentChanged = normalizedComment != currentComment

            if (ratingChanged) {
                when (val result = reviewRepository.updateReview(review.id, UpdateReviewRequest(rating))) {
                    is NetworkResult.Success -> Unit
                    is NetworkResult.Error -> {
                        _editReviewResult.value = NetworkResult.Error(result.message)
                        return@launch
                    }
                    is NetworkResult.Loading -> Unit
                }
            }

            if (commentChanged) {
                val commentError = when {
                    normalizedComment.isBlank() && review.comment != null -> {
                        when (val result = commentRepository.deleteComment(review.comment.id)) {
                            is NetworkResult.Success -> null
                            is NetworkResult.Error -> result.message
                            is NetworkResult.Loading -> null
                        }
                    }
                    normalizedComment.isNotBlank() && review.comment != null -> {
                        when (val result = commentRepository.updateComment(review.comment.id, normalizedComment)) {
                            is NetworkResult.Success -> null
                            is NetworkResult.Error -> result.message
                            is NetworkResult.Loading -> null
                        }
                    }
                    normalizedComment.isNotBlank() -> {
                        when (val result = reviewRepository.addCommentToReview(
                            review.id,
                            AddCommentToReviewRequest(normalizedComment)
                        )) {
                            is NetworkResult.Success -> null
                            is NetworkResult.Error -> result.message
                            is NetworkResult.Loading -> null
                        }
                    }
                    else -> null
                }

                if (commentError != null) {
                    _editReviewResult.value = NetworkResult.Error(commentError)
                    return@launch
                }
            }

            loadMyReviews()
            if (commentChanged) {
                loadMyComments()
            }
            _editReviewResult.value = NetworkResult.Success(Unit)
        }
    }

    fun deleteReview(reviewId: Long) {
        viewModelScope.launch {
            _deleteReviewResult.value = NetworkResult.Loading
            val result = reviewRepository.deleteReview(reviewId)
            _deleteReviewResult.value = result
            if (result is NetworkResult.Success) {
                loadMyReviews()
                loadMyComments()
            }
        }
    }

    fun resetEditReview() { _editReviewResult.value = null }
    fun resetDeleteReview() { _deleteReviewResult.value = null }
}
