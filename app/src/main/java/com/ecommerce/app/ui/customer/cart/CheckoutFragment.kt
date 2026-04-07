package com.ecommerce.app.ui.customer.cart

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.model.address.AddressResponse
import com.ecommerce.app.data.model.order.CheckoutRequest
import com.ecommerce.app.data.model.shipping.FreightResponse
import com.ecommerce.app.databinding.FragmentCheckoutBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CheckoutViewModel by viewModels()

    private var addresses = listOf<AddressResponse>()
    private var freightOptions = listOf<FreightResponse>()
    private var cartSubtotal = 0.0

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        setupPaymentMethods()
        setupAddressSpinnerListener()
        observeCart()
        observeAddresses()
        observeFreight()
        observeCheckout()

        viewModel.loadInitialData()

        binding.btnPlaceOrder.setOnClickListener { placeOrder() }
    }

    private fun setupPaymentMethods() {
        val methods = listOf("CREDIT_CARD")
        binding.spinnerPayment.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, methods
        )
    }

    private fun setupAddressSpinnerListener() {
        binding.spinnerAddress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View?, pos: Int, id: Long) {
                val address = addresses.getOrNull(pos) ?: return
                val postalCode = address.postalCode?.replace("-", "")?.trim().orEmpty()
                if (postalCode.length == 8) viewModel.loadFreight(postalCode)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun observeCart() {
        viewModel.cartState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    cartSubtotal = result.data.total
                    buildOrderItemsView(result.data)
                    updateTotals()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun observeAddresses() {
        viewModel.addressesState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    addresses = result.data.content
                    if (addresses.isEmpty()) {
                        showToast("Please add a delivery address first")
                        binding.btnPlaceOrder.isEnabled = false
                        return@observe
                    }
                    val labels = addresses.map { "${it.name} — ${it.street}, ${it.number}" }
                    binding.spinnerAddress.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        labels
                    )
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun observeFreight() {
        viewModel.freightState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.tvFreightLabel.hide()
                    binding.spinnerFreight.hide()
                    binding.tvFreightCost.text = "—"
                    updateTotals()
                }
                is NetworkResult.Success -> {
                    freightOptions = result.data
                    if (freightOptions.isEmpty()) {
                        binding.tvFreightLabel.hide()
                        binding.spinnerFreight.hide()
                        binding.tvFreightCost.text = "—"
                        updateTotals()
                        return@observe
                    }

                    val labels = freightOptions.map {
                        "${it.name} — ${currencyFormat.format(it.price)} (${it.deliveryDays}d)"
                    }
                    binding.spinnerFreight.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        labels
                    )

                    // Update cost summary whenever the user picks a different freight option
                    binding.spinnerFreight.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>, v: View?, pos: Int, id: Long
                            ) { updateTotals() }
                            override fun onNothingSelected(parent: AdapterView<*>) {}
                        }

                    binding.tvFreightLabel.show()
                    binding.spinnerFreight.show()
                    updateTotals()
                }
                is NetworkResult.Error -> {
                    freightOptions = emptyList()
                    binding.tvFreightLabel.hide()
                    binding.spinnerFreight.hide()
                    binding.tvFreightCost.text = "—"
                    updateTotals()
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

                    val checkoutUrl = result.data.checkoutUrl
                    val orderId = result.data.orderId

                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl)))

                    findNavController().navigate(
                        CheckoutFragmentDirections
                            .actionCheckoutFragmentToPaymentWaitingFragment(orderId)
                    )
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnPlaceOrder.isEnabled = true
                    showToast(result.message)
                }
            }
        }
    }

    private fun buildOrderItemsView(cart: com.ecommerce.app.data.model.cart.CartResponse) {
        val container = binding.llOrderItems
        container.removeAllViews()
        cart.items.forEach { item ->
            val row = layoutInflater.inflate(
                android.R.layout.simple_list_item_2, container, false
            )
            row.findViewById<TextView>(android.R.id.text1).apply {
                text = "${item.quantity}× ${item.name}"
                textSize = 14f
            }
            row.findViewById<TextView>(android.R.id.text2).apply {
                text = currencyFormat.format(item.subtotal)
                textSize = 13f
            }
            container.addView(row)
        }
    }

    private fun updateTotals() {
        val selectedFreight = freightOptions.getOrNull(
            binding.spinnerFreight.selectedItemPosition
        )
        val freightCost = selectedFreight?.price ?: 0.0
        val total = cartSubtotal + freightCost

        binding.tvSubtotal.text = currencyFormat.format(cartSubtotal)
        binding.tvFreightCost.text = if (selectedFreight != null)
            currencyFormat.format(freightCost) else "—"
        binding.tvTotal.text = currencyFormat.format(total)
    }

    private fun placeOrder() {
        if (addresses.isEmpty()) {
            showToast("Please add a delivery address first")
            return
        }
        val selectedAddress = addresses[binding.spinnerAddress.selectedItemPosition]
        val paymentMethod = binding.spinnerPayment.selectedItem.toString()

        val selectedFreight = freightOptions.getOrNull(
            binding.spinnerFreight.selectedItemPosition
        )
        if (binding.spinnerFreight.visibility == View.VISIBLE && selectedFreight == null) {
            showToast("Please select a shipping option")
            return
        }

        viewModel.checkout(
            CheckoutRequest(
                addressId = selectedAddress.id,
                paymentMethod = paymentMethod
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}