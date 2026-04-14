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
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ecommerce.app.R
import com.ecommerce.app.data.model.address.AddressResponse
import com.ecommerce.app.data.model.cart.CartResponse
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
    private var initialFreightLoaded = false

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

        binding.layoutLoading.show()
        binding.scrollContent.hide()

        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        setupPaymentMethods()
        setupAddressSpinnerListener()
        observeCart()
        observeAddresses()
        observeFreight()
        observeCheckout()

        viewModel.loadInitialData()

        binding.btnPlaceOrder.setOnClickListener { placeOrder() }
    }
    private fun checkIfInitialDataReady() {
        val cartReady = viewModel.cartState.value is NetworkResult.Success
                || viewModel.cartState.value is NetworkResult.Error
        val addressReady = viewModel.addressesState.value is NetworkResult.Success
                || viewModel.addressesState.value is NetworkResult.Error
        val freightReady = viewModel.freightState.value is NetworkResult.Success
                || viewModel.freightState.value is NetworkResult.Error

        if (cartReady && addressReady && freightReady) {
            binding.layoutLoading.hide()
            binding.scrollContent.show()
        }
    }

    private data class PaymentMethod(val label: String, val value: String)

    private val paymentMethods = listOf(
        PaymentMethod("Cartão de Crédito", "CREDIT_CARD")
    )

    private fun setupPaymentMethods() {
        val labels = paymentMethods.map { it.label }
        binding.spinnerPayment.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            labels
        )
    }

    private fun setupAddressSpinnerListener() {
        binding.spinnerAddress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View?, pos: Int, id: Long) {
                val address = addresses.getOrNull(pos) ?: return
                val postalCode = address.postalCode?.replace("-", "")?.trim().orEmpty()
                if (postalCode.length == 8) {
                    viewModel.loadFreight(postalCode)
                } else {
                    if (!initialFreightLoaded) {
                        initialFreightLoaded = true
                        checkIfInitialDataReady()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun observeCart() {
        viewModel.cartState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> Unit
                is NetworkResult.Success -> {
                    cartSubtotal = result.data.total
                    buildOrderItemsView(result.data)
                    updateTotals()
                    checkIfInitialDataReady()
                }
                is NetworkResult.Error -> {
                    showToast(result.message)
                    checkIfInitialDataReady()
                }
            }
        }
    }

    private fun observeAddresses() {
        viewModel.addressesState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> Unit
                is NetworkResult.Success -> {
                    addresses = result.data.content
                    if (addresses.isEmpty()) {
                        showToast("Please add a delivery address first")
                        binding.btnPlaceOrder.isEnabled = false
                    } else {
                        val labels = addresses.map { "${it.name} — ${it.street}, ${it.number}" }
                        binding.spinnerAddress.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            labels
                        )
                    }
                    checkIfInitialDataReady()
                }
                is NetworkResult.Error -> {
                    showToast(result.message)
                    checkIfInitialDataReady()
                }
            }
        }
    }

    private fun observeFreight() {
        viewModel.freightState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> Unit
                is NetworkResult.Success -> {
                    freightOptions = result.data
                    if (freightOptions.isEmpty()) {
                        binding.tvFreightLabel.hide()
                        binding.spinnerFreight.hide()
                        binding.cardOrderSummary.hide()
                        binding.btnPlaceOrder.hide()
                        binding.tvFreightCost.text = "—"
                    } else {
                        val labels = freightOptions.map {
                            "${it.name} — ${currencyFormat.format(it.price)} (${it.deliveryDays}d)"
                        }
                        binding.spinnerFreight.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            labels
                        )
                        binding.spinnerFreight.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>, v: View?, pos: Int, id: Long
                                ) { updateTotals() }
                                override fun onNothingSelected(parent: AdapterView<*>) {}
                            }
                        binding.tvFreightLabel.show()
                        binding.spinnerFreight.show()
                        binding.cardOrderSummary.show()
                        binding.btnPlaceOrder.show()
                        updateTotals()
                    }
                    checkIfInitialDataReady()
                }
                is NetworkResult.Error -> {
                    freightOptions = emptyList()
                    binding.tvFreightLabel.hide()
                    binding.spinnerFreight.hide()
                    binding.cardOrderSummary.hide()
                    binding.btnPlaceOrder.hide()
                    binding.tvFreightCost.text = "—"
                    checkIfInitialDataReady()
                }
            }
        }
    }

    private fun observeCheckout() {
        viewModel.checkoutState.observe(viewLifecycleOwner) { result ->
            result ?: return@observe

            when (result) {
                is NetworkResult.Loading -> {
                    binding.btnPlaceOrder.isEnabled = false
                    binding.layoutLoading.show()
                    binding.scrollContent.hide()
                }
                is NetworkResult.Success -> {
                    val checkoutUrl = result.data.checkoutUrl
                    val orderId = result.data.orderId

                    viewModel.onCheckoutHandled()

                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl)))

                    findNavController().navigate(
                        R.id.action_checkoutFragment_to_paymentWaitingFragment,
                        bundleOf("orderId" to orderId)
                    )
                }
                is NetworkResult.Error -> {
                    binding.btnPlaceOrder.isEnabled = true
                    binding.layoutLoading.hide()
                    binding.scrollContent.show()
                    viewModel.onCheckoutHandled()
                    showToast(result.message)
                }
            }
        }
    }

    private fun buildOrderItemsView(cart: CartResponse) {
        val container = binding.llOrderItems
        container.removeAllViews()
        cart.items.forEach { item ->
            val row = layoutInflater.inflate(R.layout.item_checkout_image, container, false)

            row.findViewById<TextView>(R.id.tv_name).text = "${item.name}"

            row.findViewById<TextView>(R.id.tv_price_quantity).text =
                "${item.quantity} × ${currencyFormat.format(item.price)}"

            row.findViewById<TextView>(R.id.tv_subtotal).text =
                currencyFormat.format(item.subtotal)

            Glide.with(this)
                .load(item.imageUrl)
                .centerCrop()
                .into(row.findViewById(R.id.iv_product))

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
        val paymentMethod = paymentMethods[binding.spinnerPayment.selectedItemPosition].value

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
                paymentMethod = paymentMethod,
                freightServiceId = selectedFreight?.serviceId ?: 0
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
