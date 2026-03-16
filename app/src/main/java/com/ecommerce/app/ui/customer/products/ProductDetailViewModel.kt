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

    private val _productState = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val productState: LiveData<NetworkResult<ProductDetailsResponse>> = _productState

    private val _cartActionState = MutableLiveData<NetworkResult<CartItemResponse>>()
    val cartActionState: LiveData<NetworkResult<CartItemResponse>> = _cartActionState

    fun loadProduct(id: Long) {
        viewModelScope.launch {
            _productState.value = NetworkResult.Loading
            _productState.value = productRepository.getProductById(id)
        }
    }

    fun addToCart(productId: Long) {
        viewModelScope.launch {
            _cartActionState.value = NetworkResult.Loading
            _cartActionState.value = cartRepository.addToCart(productId)
        }
    }
}
