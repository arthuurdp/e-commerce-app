package com.ecommerce.app.ui.admin.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.model.UserResponse
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.databinding.FragmentAdminUsersBinding
import com.ecommerce.app.databinding.ItemUserBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ── ViewModel ─────────────────────────────────────────────────────────────────

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

// ── Fragment ──────────────────────────────────────────────────────────────────

@AndroidEntryPoint
class AdminUsersFragment : Fragment() {

    private var _binding: FragmentAdminUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminUsersViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdminUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter()
        binding.rvUsers.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener { viewModel.loadUsers() }

        viewModel.usersState.observe(viewLifecycleOwner) { result ->
            binding.swipeRefresh.isRefreshing = false
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(result.data.content)
                    binding.tvEmpty.visibility =
                        if (result.data.content.isEmpty()) View.VISIBLE else View.GONE
                }
                is NetworkResult.Error -> { binding.progressBar.hide(); showToast(result.message) }
            }
        }
        viewModel.loadUsers()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

// ── Adapter ───────────────────────────────────────────────────────────────────

class UserAdapter : ListAdapter<UserResponse, UserAdapter.VH>(Diff) {

    inner class VH(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val user = getItem(position)
        holder.binding.tvName.text  = "${user.firstName} ${user.lastName}"
        holder.binding.tvEmail.text = user.email
        holder.binding.tvRole.text  = "USER"
    }

    object Diff : DiffUtil.ItemCallback<UserResponse>() {
        override fun areItemsTheSame(a: UserResponse, b: UserResponse) = a.id == b.id
        override fun areContentsTheSame(a: UserResponse, b: UserResponse) = a == b
    }
}
