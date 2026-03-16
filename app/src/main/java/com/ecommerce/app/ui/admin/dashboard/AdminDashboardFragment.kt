package com.ecommerce.app.ui.admin.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentAdminDashboardBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminDashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminDashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStats()
        viewModel.loadStats()

        binding.cardProducts.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminProductsFragment)
        }
        binding.cardOrders.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminOrdersFragment)
        }
        binding.cardUsers.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminUsersFragment)
        }
        binding.cardCategories.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminCategoriesFragment)
        }
    }

    private fun observeStats() {
        viewModel.stats.observe(viewLifecycleOwner) { stats ->
            binding.progressBar.hide()
            binding.tvTotalProducts.text = "Products: ${stats.totalProducts}"
            binding.tvTotalOrders.text   = "Orders: ${stats.totalOrders}"
            binding.tvTotalUsers.text    = "Users: ${stats.totalUsers}"
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBar.show() else binding.progressBar.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
