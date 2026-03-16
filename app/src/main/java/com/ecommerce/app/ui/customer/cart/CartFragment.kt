package com.ecommerce.app.ui.customer.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
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

                    binding.tvTotal.text = "Total: ${cart.total.toCurrency()}"
                    binding.tvItemCount.text = "${cart.totalQuantity} items"
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
