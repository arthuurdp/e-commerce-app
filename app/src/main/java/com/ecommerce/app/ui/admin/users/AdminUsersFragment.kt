package com.ecommerce.app.ui.admin.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecommerce.app.databinding.FragmentAdminUsersBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

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