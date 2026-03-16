package com.ecommerce.app.ui.customer.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.model.AddressResponse
import com.ecommerce.app.data.model.CheckoutRequest
import com.ecommerce.app.databinding.FragmentCheckoutBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CheckoutViewModel by viewModels()

    private var addresses = listOf<AddressResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPaymentMethods()
        observeAddresses()
        observeCheckout()

        viewModel.loadAddresses()

        binding.btnPlaceOrder.setOnClickListener { placeOrder() }
    }

    private fun setupPaymentMethods() {
        val methods = listOf("CREDIT_CARD", "PIX", "BOLETO")
        binding.spinnerPayment.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, methods
        )
    }

    private fun observeAddresses() {
        viewModel.addressesState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    addresses = result.data.content
                    val labels = addresses.map { "${it.name} - ${it.street}, ${it.number}" }
                    binding.spinnerAddress.adapter = ArrayAdapter(
                        requireContext(), android.R.layout.simple_spinner_dropdown_item, labels
                    )
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun observeCheckout() {
        viewModel.checkoutState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnPlaceOrder.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.btnPlaceOrder.isEnabled = true
                    showToast("Order placed! Redirecting to payment…")
                    // In a real app, open checkoutUrl in a WebView or Custom Tab
                    findNavController().navigateUp()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnPlaceOrder.isEnabled = true
                    showToast(result.message)
                }
            }
        }
    }

    private fun placeOrder() {
        if (addresses.isEmpty()) {
            showToast("Please add a delivery address first")
            return
        }
        val selectedAddress = addresses[binding.spinnerAddress.selectedItemPosition]
        val paymentMethod   = binding.spinnerPayment.selectedItem.toString()
        viewModel.checkout(CheckoutRequest(selectedAddress.id, paymentMethod))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
