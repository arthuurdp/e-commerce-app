package com.ecommerce.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.R
import com.ecommerce.app.util.JwtDecoder
import com.ecommerce.app.util.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _startDestination = MutableStateFlow(R.id.loginFragment)
    val startDestination: StateFlow<Int> = _startDestination

    init {
        viewModelScope.launch {
            val token = tokenManager.getToken()
            _startDestination.value = when {
                token.isNullOrBlank()       -> R.id.loginFragment
                JwtDecoder.isExpired(token) -> {
                    tokenManager.clearToken()
                    R.id.loginFragment
                }
                tokenManager.getRole() == "ROLE_ADMIN" -> R.id.adminDashboardFragment
                else -> R.id.homeFragment
            }
        }
    }
}
