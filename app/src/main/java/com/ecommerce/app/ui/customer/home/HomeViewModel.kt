package com.ecommerce.app.ui.customer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.model.ProductResponse
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productsState = MutableLiveData<NetworkResult<PageResponse<ProductResponse>>>()
    val productsState: LiveData<NetworkResult<PageResponse<ProductResponse>>> = _productsState

    private var currentPage = 0
    private var isLastPage = false

    fun loadProducts(page: Int = 0, name: String? = null, categoryIds: List<Long>? = null) {
        if (page > 0 && isLastPage) return
        viewModelScope.launch {
            _productsState.value = NetworkResult.Loading
            val result = productRepository.getProducts(page, 20, name, categoryIds)
            if (result is NetworkResult.Success) {
                currentPage = result.data.number
                isLastPage = result.data.last
            }
            _productsState.value = result
        }
    }

    fun loadNextPage() {
        if (!isLastPage) loadProducts(currentPage + 1)
    }
}
