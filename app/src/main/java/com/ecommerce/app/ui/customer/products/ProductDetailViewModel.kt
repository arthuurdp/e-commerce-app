package com.ecommerce.app.ui.customer.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.CartItemResponse
import com.ecommerce.app.data.model.ProductDetailsResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _product = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val product: LiveData<NetworkResult<ProductDetailsResponse>> = _product

    private val _addToCartState = MutableLiveData<NetworkResult<CartItemResponse>>()
    val addToCartState: LiveData<NetworkResult<CartItemResponse>> = _addToCartState

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
}