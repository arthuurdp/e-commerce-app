package com.ecommerce.app.ui.customer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.cart.CartResponse
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.product.HomeProductsResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.CategoryRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _productsByCategory = MutableLiveData<List<HomeProductsResponse>>()
    val productsByCategory: LiveData<List<HomeProductsResponse>> = _productsByCategory

    private val _firstName = MutableLiveData<NetworkResult<String>>()
    val firstName: LiveData<NetworkResult<String>> = _firstName

    private val _cartState = MutableLiveData<NetworkResult<CartResponse>>()
    val cartState: LiveData<NetworkResult<CartResponse>> = _cartState

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _categoriesState = MutableLiveData<NetworkResult<PageResponse<CategoryResponse>>>()
    val categoriesState: LiveData<NetworkResult<PageResponse<CategoryResponse>>> = _categoriesState

    private var hasLoadedOnce = false

    init {
        loadAllData()
    }

    fun loadAllData(forceRefresh: Boolean = false) {
        if (hasLoadedOnce && !forceRefresh) return

        viewModelScope.launch {
            _isLoading.value = true

            val homeDeferred = async { productRepository.getHomeProducts() }
            val userDeferred = async { userRepository.getCurrentUser() }
            val cartDeferred = async { cartRepository.getCart() }
            val categoriesDeferred = async { categoryRepository.getCategories() }

            val homeResult = homeDeferred.await()
            val userResult = userDeferred.await()
            val cartResult = cartDeferred.await()
            val categoriesResult = categoriesDeferred.await()

            if (homeResult is NetworkResult.Success) {
                _productsByCategory.value = homeResult.data
            }

            _categoriesState.value = categoriesResult

            _firstName.value = if (userResult is NetworkResult.Success) {
                NetworkResult.Success(userResult.data.firstName + "!")
            } else {
                NetworkResult.Error("Error")
            }

            _cartState.value = cartResult
            _isLoading.value = false
            hasLoadedOnce = true
        }
    }

    fun refresh() = loadAllData(forceRefresh = true)

    fun loadCart() {
        viewModelScope.launch {
            _cartState.value = cartRepository.getCart()
        }
    }
}