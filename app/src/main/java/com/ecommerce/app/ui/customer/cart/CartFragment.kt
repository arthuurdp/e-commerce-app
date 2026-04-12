package com.ecommerce.app.ui.customer.cart

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentCartBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import com.ecommerce.app.util.toCurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()
    private lateinit var cartAdapter: CartItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeCart()
        observeAddressStatus()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCheckout.setOnClickListener {
            viewModel.checkAddressesBeforeCheckout()
        }

        binding.btnClearCart.setOnClickListener {
            viewModel.clearCart()
        }

        viewModel.loadCart()
    }

    private fun setupRecyclerView() {
        cartAdapter = CartItemAdapter(
            onIncrement = { productId -> viewModel.addToCart(productId) },
            onDecrement = { productId -> viewModel.removeFromCart(productId) }
        )
        binding.rvCartItems.adapter = cartAdapter
    }

    private fun observeCart() {
        viewModel.cartState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val cart = result.data
                    cartAdapter.submitList(cart.items)

                    val hasItems = cart.items.isNotEmpty()
                    binding.layoutCartFooter.visibility = if (hasItems) View.VISIBLE else View.GONE
                    binding.tvEmptyCart.visibility = if (hasItems) View.GONE else View.VISIBLE

                    binding.tvTotal.text = cart.total.toCurrency()
                    binding.tvItemCount.text = "${cart.totalQuantity} ${if (cart.totalQuantity == 1) "item" else "itens"}"
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun observeAddressStatus() {
        viewModel.addressStatus.observe(viewLifecycleOwner) { result ->
            result ?: return@observe
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    viewModel.onAddressStatusHandled()
                    if (result.data) {
                        findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
                    } else {
                        showAddAddressDialog()
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    viewModel.onAddressStatusHandled()
                    showToast(result.message)
                }
            }
        }
    }

    private fun showAddAddressDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_first_address)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.80).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.findViewById<View>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<View>(R.id.btnConfirm).setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_cartFragment_to_addAddressFragment)
        }

        dialog.show()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
