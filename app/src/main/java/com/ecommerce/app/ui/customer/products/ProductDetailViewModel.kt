package com.ecommerce.app.ui.customer.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.cart.CartItemResponse
import com.ecommerce.app.data.model.comment.CommentRequest
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.product.ProductDetailsResponse
import com.ecommerce.app.data.model.review.ReviewRequest
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.CommentRepository
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
    private val favoriteRepository: FavoriteRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {

    private val _product = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val product: LiveData<NetworkResult<ProductDetailsResponse>> = _product

    private val _addToCartState = MutableLiveData<NetworkResult<CartItemResponse>?>()
    val addToCartState: LiveData<NetworkResult<CartItemResponse>?> = _addToCartState

    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    private val _userId = MutableLiveData<Long?>()
    val userId: LiveData<Long?> = _userId

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

    // ── Comments ─────────────────────────────────────────────────────────────

    private val _commentsState = MutableLiveData<NetworkResult<List<CommentResponse>>>()
    val commentsState: LiveData<NetworkResult<List<CommentResponse>>> = _commentsState

    private val _addCommentState = MutableLiveData<NetworkResult<CommentResponse>?>()
    val addCommentState: LiveData<NetworkResult<CommentResponse>?> = _addCommentState

    private val _deleteCommentState = MutableLiveData<NetworkResult<Unit>?>()
    val deleteCommentState: LiveData<NetworkResult<Unit>?> = _deleteCommentState

    init {
        loadUserInfo()
    }

    fun loadProduct(id: Long) {
        viewModelScope.launch {
            _product.value = NetworkResult.Loading
            _product.value = productRepository.getProductById(id)
            loadReviews(id)
            loadAverageRating(id)
            loadComments(id)
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

    fun loadComments(productId: Long) {
        viewModelScope.launch {
            _commentsState.value = NetworkResult.Loading
            _commentsState.value = commentRepository.getProductComments(productId)
        }
    }

    fun addComment(productId: Long, content: String) {
        viewModelScope.launch {
            _addCommentState.value = NetworkResult.Loading
            val result = commentRepository.createComment(productId, content)
            _addCommentState.value = result
            if (result is NetworkResult.Success) {
                loadComments(productId)
            }
        }
    }

    fun deleteComment(commentId: Long, productId: Long) {
        viewModelScope.launch {
            _deleteCommentState.value = NetworkResult.Loading
            val result = commentRepository.deleteComment(commentId)
            _deleteCommentState.value = result
            if (result is NetworkResult.Success) {
                loadComments(productId)
            }
        }
    }

    fun resetAddCommentState() { _addCommentState.value = null }
    fun resetDeleteCommentState() { _deleteCommentState.value = null }

    // ── Reviews ───────────────────────────────────────────────────────────────

    fun addReview(productId: Long, rating: Int, comment: String?) {
        viewModelScope.launch {
            _addReviewState.value = NetworkResult.Loading
            val commentRequest = comment?.let {
                if (it.isNotBlank()) CommentRequest(productId, it) else null
            }
            val request = ReviewRequest(productId, rating, commentRequest)
            val result = reviewRepository.createReview(productId, request)
            _addReviewState.value = result
            if (result is NetworkResult.Success) {
                loadReviews(productId)
                loadAverageRating(productId)
            }
        }
    }

    fun resetAddReviewState() { _addReviewState.value = null }

    // ── Favorites ─────────────────────────────────────────────────────────────

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

    fun resetFavoriteState() { _favoriteState.value = null }

    // ── Cart ──────────────────────────────────────────────────────────────────

    fun addToCart(productId: Long) {
        viewModelScope.launch {
            _addToCartState.value = NetworkResult.Loading
            _addToCartState.value = cartRepository.addToCart(productId)
        }
    }

    fun resetAddToCartState() { _addToCartState.value = null }

    // ── User ──────────────────────────────────────────────────────────────────

    private fun loadUserInfo() {
        viewModelScope.launch {
            val result = userRepository.getCurrentUser()
            if (result is NetworkResult.Success) {
                _userEmail.value = result.data.email
                _userId.value = result.data.id
            }
        }
    }
}