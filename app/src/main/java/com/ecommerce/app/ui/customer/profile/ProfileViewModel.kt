package com.ecommerce.app.ui.customer.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.user.UpdateUserRequest
import com.ecommerce.app.data.model.user.UserResponse
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _profileState = MutableLiveData<NetworkResult<UserResponse>>()
    val profileState: LiveData<NetworkResult<UserResponse>> = _profileState

    private val _updateState = MutableLiveData<NetworkResult<UserResponse>>()
    val updateState: LiveData<NetworkResult<UserResponse>> = _updateState

    fun loadProfile() {
        viewModelScope.launch {
            _profileState.value = NetworkResult.Loading
            _profileState.value = userRepository.getCurrentUser()
        }
    }

    fun updateProfile(firstName: String?, lastName: String?, phone: String?) {
        viewModelScope.launch {
            _updateState.value = NetworkResult.Loading
            _updateState.value = userRepository.updateCurrentUser(
                UpdateUserRequest(firstName = firstName, lastName = lastName, phone = phone)
            )
        }
    }
    fun logout() {
        viewModelScope.launch { tokenManager.clearToken() }
    }
}