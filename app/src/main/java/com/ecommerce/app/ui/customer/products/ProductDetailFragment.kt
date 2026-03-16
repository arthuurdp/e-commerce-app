package com.ecommerce.app.ui.customer.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentProductDetailBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import com.ecommerce.app.util.toCurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeProduct()
        observeCartAction()
        viewModel.loadProduct(args.productId)

        binding.btnAddToCart.setOnClickListener {
            viewModel.addToCart(args.productId)
        }
    }

    private fun observeProduct() {
        viewModel.productState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val product = result.data

                    binding.tvName.text = product.name
                    binding.tvPrice.text = product.price.toCurrency()
                    binding.tvDescription.text = product.description
                    binding.tvCategories.text =
                        product.categories.joinToString(", ") { it.name }

                    // Show weight/dimensions
                    binding.tvDimensions.text =
                        "${product.weight}kg · ${product.width}×${product.height}×${product.length} cm"

                    // Load main image
                    val mainImageUrl = product.imgs
                        .firstOrNull { it.mainImage }?.url
                        ?: product.imgs.firstOrNull()?.url

                    Glide.with(this)
                        .load(mainImageUrl)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .into(binding.ivProduct)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun observeCartAction() {
        viewModel.cartActionState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.btnAddToCart.isEnabled = false
                    binding.btnAddToCart.text = "Adding…"
                }
                is NetworkResult.Success -> {
                    binding.btnAddToCart.isEnabled = true
                    binding.btnAddToCart.text = "Add to Cart"
                    showToast("Added to cart!")
                }
                is NetworkResult.Error -> {
                    binding.btnAddToCart.isEnabled = true
                    binding.btnAddToCart.text = "Add to Cart"
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
