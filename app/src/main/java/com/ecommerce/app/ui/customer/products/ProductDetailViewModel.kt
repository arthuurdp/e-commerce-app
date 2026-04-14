package com.ecommerce.app.ui.customer.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.cart.CartItemResponse
import com.ecommerce.app.data.model.product.ProductDetailsResponse
import com.ecommerce.app.data.model.review.ReviewRequest
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.FavoriteRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.data.repository.ReviewRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _product = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val product: LiveData<NetworkResult<ProductDetailsResponse>> = _product

    private val _addToCartState = MutableLiveData<NetworkResult<CartItemResponse>?>()
    val addToCartState: LiveData<NetworkResult<CartItemResponse>?> = _addToCartState

    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    private val _reviews = MutableLiveData<NetworkResult<List<ReviewResponse>>>()
    val reviews: LiveData<NetworkResult<List<ReviewResponse>>> = _reviews

    private val _averageRating = MutableLiveData<NetworkResult<Double>>()
    val averageRating: LiveData<NetworkResult<Double>> = _averageRating

    private val _addReviewState = MutableLiveData<NetworkResult<ReviewResponse>?>()
    val addReviewState: LiveData<NetworkResult<ReviewResponse>?> = _addReviewState

    private val _favoriteState = MutableLiveData<NetworkResult<Unit>?>()
    val favoriteState: LiveData<NetworkResult<Unit>?> = _favoriteState

    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        loadUserEmail()
    }

    fun loadProduct(id: Long) {
        viewModelScope.launch {
            _product.value = NetworkResult.Loading
            _product.value = productRepository.getProductById(id)
            loadReviews(id)
            loadAverageRating(id)
            checkIfFavorite(id)
        }
    }

    fun loadReviews(productId: Long) {
        viewModelScope.launch {
            _reviews.value = NetworkResult.Loading
            _reviews.value = reviewRepository.getProductReviews(productId)
        }
    }

    fun loadAverageRating(productId: Long) {
        viewModelScope.launch {
            _averageRating.value = reviewRepository.getAverageRating(productId)
        }
    }

    fun addReview(productId: Long, rating: Int, comment: String?) {
        viewModelScope.launch {
            _addReviewState.value = NetworkResult.Loading
            val request = ReviewRequest(productId, rating, comment)
            val result = reviewRepository.createReview(request)
            _addReviewState.value = result
            if (result is NetworkResult.Success) {
                loadReviews(productId)
                loadAverageRating(productId)
            }
        }
    }

    fun toggleFavorite(productId: Long) {
        viewModelScope.launch {
            _favoriteState.value = NetworkResult.Loading
            val currentFavorite = _isFavorite.value ?: false
            val result = if (currentFavorite) {
                favoriteRepository.removeFavorite(productId)
            } else {
                favoriteRepository.addFavorite(productId)
            }
            
            _favoriteState.value = result
            if (result is NetworkResult.Success) {
                _isFavorite.value = !currentFavorite
            }
        }
    }

    private fun checkIfFavorite(productId: Long) {
        viewModelScope.launch {
            val result = favoriteRepository.getUserFavorites()
            if (result is NetworkResult.Success) {
                _isFavorite.value = result.data.any { it.id == productId }
            }
        }
    }

    fun resetAddReviewState() {
        _addReviewState.value = null
    }

    fun resetFavoriteState() {
        _favoriteState.value = null
    }

    fun addToCart(productId: Long) {
        viewModelScope.launch {
            _addToCartState.value = NetworkResult.Loading

            val result = cartRepository.addToCart(productId)

            _addToCartState.value = result
        }
    }

    fun resetAddToCartState() {
        _addToCartState.value = null
    }

    private fun loadUserEmail() {
        viewModelScope.launch {
            val result = userRepository.getCurrentUser()
            if (result is NetworkResult.Success) {
                _userEmail.value = result.data.email
            }
        }
    }

}