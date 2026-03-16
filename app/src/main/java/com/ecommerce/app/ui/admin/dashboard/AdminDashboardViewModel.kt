package com.ecommerce.app.ui.admin.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardStats(
    val totalProducts: Long = 0,
    val totalOrders: Long = 0,
    val totalUsers: Long = 0
)

@HiltViewModel
class AdminDashboardViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _stats = MutableLiveData<DashboardStats>()
    val stats: LiveData<DashboardStats> = _stats

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadStats() {
        viewModelScope.launch {
            _isLoading.value = true

            // Load all 3 counts concurrently
            val productsDeferred = async { productRepository.getProducts(size = 1) }
            val ordersDeferred   = async { orderRepository.getOrders(size = 1) }
            val usersDeferred    = async { userRepository.getAllUsers(size = 1) }

            val products = productsDeferred.await()
            val orders   = ordersDeferred.await()
            val users    = usersDeferred.await()

            _stats.value = DashboardStats(
                totalProducts = if (products is NetworkResult.Success) products.data.totalElements else 0,
                totalOrders   = if (orders   is NetworkResult.Success) orders.data.totalElements   else 0,
                totalUsers    = if (users    is NetworkResult.Success) users.data.totalElements    else 0
            )
            _isLoading.value = false
        }
    }
}
