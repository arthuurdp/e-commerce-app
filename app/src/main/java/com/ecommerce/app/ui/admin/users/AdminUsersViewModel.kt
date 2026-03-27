package com.ecommerce.app.ui.admin.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.user.UserResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminUsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _usersState = MutableLiveData<NetworkResult<PageResponse<UserResponse>>>()
    val usersState: LiveData<NetworkResult<PageResponse<UserResponse>>> = _usersState

    fun loadUsers() {
        viewModelScope.launch {
            _usersState.value = NetworkResult.Loading
            _usersState.value = userRepository.getAllUsers(size = 50)
        }
    }
}