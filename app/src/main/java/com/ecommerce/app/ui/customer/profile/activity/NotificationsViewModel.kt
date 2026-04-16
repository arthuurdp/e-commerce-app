package com.ecommerce.app.ui.customer.profile.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.data.repository.FavoriteRepository
import com.ecommerce.app.data.repository.UserActivityRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val userActivityRepository: UserActivityRepository,
    private val favoriteRepository: FavoriteRepository
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

    init {
        loadRecentActivity()
    }

    fun loadRecentActivity() {
        viewModelScope.launch {
            _recentActivity.value = NetworkResult.Loading
            _recentActivity.value = userActivityRepository.getRecentActivity()
        }
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

    fun removeFavorite(productId: Long) {
        viewModelScope.launch {
            _removeFavoriteResult.value = NetworkResult.Loading
            val result = favoriteRepository.removeFavorite(productId)
            _removeFavoriteResult.value = result
            if (result is NetworkResult.Success) {
                loadMyFavorites()
            }
        }
    }
}
