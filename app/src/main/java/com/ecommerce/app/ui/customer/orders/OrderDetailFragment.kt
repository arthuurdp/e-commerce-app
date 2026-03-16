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
import com.ecommerce.app.data.model.OrderItemResponse
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
                    binding.tvStatus.text   = order.status
                    binding.tvDate.text     = order.createdAt.take(10)
                    binding.tvTotal.text    = order.total.toCurrency()
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

// ── Inline adapter for order line items ──────────────────────────────────────

class OrderItemAdapter : ListAdapter<OrderItemResponse, OrderItemAdapter.VH>(Diff) {

    inner class VH(val binding: ItemOrderLineBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemOrderLineBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.binding.tvProductName.text = item.productName
        holder.binding.tvQty.text         = "× ${item.quantity}"
        holder.binding.tvUnitPrice.text   = item.unitPrice.toCurrency()
        holder.binding.tvSubtotal.text    = item.subtotal.toCurrency()
    }

    object Diff : DiffUtil.ItemCallback<OrderItemResponse>() {
        override fun areItemsTheSame(a: OrderItemResponse, b: OrderItemResponse) =
            a.productId == b.productId
        override fun areContentsTheSame(a: OrderItemResponse, b: OrderItemResponse) = a == b
    }
}
