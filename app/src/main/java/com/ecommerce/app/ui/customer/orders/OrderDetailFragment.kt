package com.ecommerce.app.ui.customer.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.databinding.FragmentOrderDetailBinding
import com.ecommerce.app.databinding.ItemOrderLineBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import com.ecommerce.app.util.toCurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment : Fragment() {

    private var _binding: FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderDetailViewModel by viewModels()
    private val args: OrderDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter = OrderItemAdapter()
        binding.rvItems.adapter = itemAdapter

        viewModel.orderState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val order = result.data
                    binding.tvOrderId.text  = "Order #${order.id}"
                    binding.tvStatus.text  = order.status
                    binding.tvDate.text = order.createdAt.take(10)
                    binding.tvTotal.text = order.total.toCurrency()
                    itemAdapter.submitList(order.items)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }

        viewModel.loadOrder(args.orderId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}