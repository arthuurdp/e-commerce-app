package com.ecommerce.app.ui.admin.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.product.CreateProductRequest
import com.ecommerce.app.data.model.product.ProductDetailsResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.product.UpdateProductRequest
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _productsState = MutableLiveData<NetworkResult<PageResponse<ProductResponse>>>()
    val productsState: LiveData<NetworkResult<PageResponse<ProductResponse>>> = _productsState

    private val _productDetailState = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val productDetailState: LiveData<NetworkResult<ProductDetailsResponse>> = _productDetailState

    private val _saveState = MutableLiveData<NetworkResult<ProductDetailsResponse>>()
    val saveState: LiveData<NetworkResult<ProductDetailsResponse>> = _saveState

    private val _deleteState = MutableLiveData<NetworkResult<Unit>>()
    val deleteState: LiveData<NetworkResult<Unit>> = _deleteState

    fun loadProducts() {
        viewModelScope.launch {
            _productsState.value = NetworkResult.Loading
            _productsState.value = productRepository.getProducts(size = 50)
        }
    }

    fun loadProductDetail(id: Long) {
        viewModelScope.launch {
            _productDetailState.value = NetworkResult.Loading
            _productDetailState.value = productRepository.getProductById(id)
        }
    }

    fun createProduct(request: CreateProductRequest) {
        viewModelScope.launch {
            _saveState.value = NetworkResult.Loading
            _saveState.value = productRepository.createProduct(request)
        }
    }

    fun updateProduct(id: Long, request: UpdateProductRequest) {
        viewModelScope.launch {
            _saveState.value = NetworkResult.Loading
            _saveState.value = productRepository.updateProduct(id, request)
        }
    }

    fun deleteProduct(id: Long) {
        viewModelScope.launch {
            _deleteState.value = NetworkResult.Loading
            _deleteState.value = productRepository.deleteProduct(id)
        }
    }
}
