package com.ecommerce.app.ui.customer.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.cart.CartItemResponse
import com.ecommerce.app.data.model.product.ProductDetailsResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _product = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val product: LiveData<NetworkResult<ProductDetailsResponse>> = _product

    private val _addToCartState = MutableLiveData<NetworkResult<CartItemResponse>?>()
    val addToCartState: LiveData<NetworkResult<CartItemResponse>?> = _addToCartState

    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    init {
        loadUserEmail()
    }

    fun loadProduct(id: Long) {
        viewModelScope.launch {
            _product.value = NetworkResult.Loading
            _product.value = productRepository.getProductById(id)
        }
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