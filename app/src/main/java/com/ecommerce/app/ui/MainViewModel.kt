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

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    init {
        viewModelScope.launch {
            val token = tokenManager.getToken()
            _isLoggedIn.value = when {
                token.isNullOrBlank() -> false
                JwtDecoder.isExpired(token) -> {
                    tokenManager.clearToken()
                    false
                }
                else -> true
            }
        }
    }
}
