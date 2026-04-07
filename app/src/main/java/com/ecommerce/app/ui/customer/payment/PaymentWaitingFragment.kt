package com.ecommerce.app.ui.customer.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentPaymentWaitingBinding
import com.ecommerce.app.ui.customer.orders.PaymentWaitingFragmentArgs
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentWaitingFragment : Fragment() {

    private var _binding: FragmentPaymentWaitingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PaymentWaitingViewModel by viewModels()
    private val args: PaymentWaitingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentWaitingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {  }

        viewModel.startPolling(args.orderId)
        observePaymentStatus()

        binding.btnViewOrders.setOnClickListener {
            findNavController().navigate(
                R.id.action_paymentWaitingFragment_to_ordersFragment,
            )
        }
    }

    fun onPaymentSuccess() = viewModel.confirmSuccess()
    fun onPaymentFailure() = viewModel.confirmFailure()

    private fun observePaymentStatus() {
        viewModel.paymentStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                PaymentStatus.WAITING -> {
                    binding.progressBar.show()
                    binding.tvTitle.text = "Waiting for payment…"
                    binding.tvSubtitle.text =
                        "Complete the payment in your browser.\nThis screen will update automatically."
                    binding.btnViewOrders.hide()
                }
                PaymentStatus.SUCCESS -> {
                    binding.progressBar.hide()
                    binding.tvTitle.text = "Payment confirmed! 🎉"
                    binding.tvSubtitle.text = "Your order has been placed successfully."
                    binding.btnViewOrders.show()
                }
                PaymentStatus.FAILURE -> {
                    binding.progressBar.hide()
                    binding.tvTitle.text = "Payment not completed"
                    binding.tvSubtitle.text =
                        "Something went wrong or the payment was cancelled."
                    binding.btnViewOrders.hide()
                }
                null -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}