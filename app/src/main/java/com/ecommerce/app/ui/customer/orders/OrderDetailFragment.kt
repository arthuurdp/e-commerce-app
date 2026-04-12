package com.ecommerce.app.ui.customer.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentOrderDetailBinding
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
    private val orderId: Long by lazy {
        requireArguments().getLong("orderId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        val itemAdapter = OrderItemAdapter()
        binding.rvItems.adapter = itemAdapter

        viewModel.orderState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()

                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val order = result.data

                    binding.tvOrderId.text = "Pedido #${order.id}"
                    binding.tvTotal.text = order.total.toCurrency()

                    val rawDate = order.createdAt.take(10)
                    binding.tvDate.text = try {
                        val parts = rawDate.split("-")
                        if (parts.size == 3) "${parts[2]}/${parts[1]}/${parts[0]}" else rawDate
                    } catch (e: Exception) { rawDate }

                    val (label, colorRes) = when (order.status) {
                        "PENDING" -> "PENDENTE" to R.color.status_pending
                        "PAID" -> "PAGO" to R.color.status_paid
                        "SHIPPED" -> "ENVIADO" to R.color.status_shipped
                        "DELIVERED" -> "ENTREGUE" to R.color.status_delivered
                        "CANCELED" -> "CANCELADO" to R.color.status_canceled
                        else -> order.status to R.color.gray
                    }
                    binding.tvStatus.text = label
                    binding.tvStatus.backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), colorRes)

                    itemAdapter.submitList(order.items)
                }

                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }

        viewModel.loadOrder(orderId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}