package com.ecommerce.app.ui.admin.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.data.model.CreateProductRequest
import com.ecommerce.app.data.model.UpdateProductRequest
import com.ecommerce.app.databinding.FragmentAdminEditProductBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminEditProductFragment : Fragment() {

    private var _binding: FragmentAdminEditProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminProductsViewModel by viewModels()
    private val args: AdminEditProductFragmentArgs by navArgs()

    private val isNewProduct get() = args.productId == -1L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminEditProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isNewProduct) {
            viewModel.loadProductDetail(args.productId)
            binding.btnDelete.show()
        } else {
            binding.btnDelete.hide()
        }

        viewModel.productDetailState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val p = result.data
                binding.etName.setText(p.name)
                binding.etDescription.setText(p.description)
                binding.etPrice.setText(p.price.toString())
                binding.etWeight.setText(p.weight.toString())
                binding.etWidth.setText(p.width.toString())
                binding.etHeight.setText(p.height.toString())
                binding.etLength.setText(p.length.toString())
                binding.etImageUrl.setText(p.imgs.firstOrNull()?.url ?: "")
                binding.etCategoryIds.setText(p.categories.joinToString(",") { it.id.toString() })
            }
        }

        viewModel.saveState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> { binding.progressBar.show(); binding.btnSave.isEnabled = false }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast(if (isNewProduct) "Product created!" else "Product updated!")
                    findNavController().navigateUp()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
                    showToast(result.message)
                }
            }
        }

        viewModel.deleteState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                showToast("Product deleted")
                findNavController().navigateUp()
            } else if (result is NetworkResult.Error) {
                showToast(result.message)
            }
        }

        binding.btnSave.setOnClickListener { save() }
        binding.btnDelete.setOnClickListener { viewModel.deleteProduct(args.productId) }
    }

    private fun save() {
        val name        = binding.etName.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        val priceStr    = binding.etPrice.text.toString().trim()
        val imageUrl    = binding.etImageUrl.text.toString().trim()
        val catIds      = binding.etCategoryIds.text.toString()
            .split(",").mapNotNull { it.trim().toLongOrNull() }

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty()) {
            showToast("Name, Description and Price are required"); return
        }

        if (isNewProduct) {
            viewModel.createProduct(
                CreateProductRequest(
                    name        = name,
                    description = description,
                    price       = priceStr.toDouble(),
                    stock       = binding.etStock.text.toString().toIntOrNull() ?: 1,
                    weight      = binding.etWeight.text.toString().toDoubleOrNull() ?: 0.5,
                    width       = binding.etWidth.text.toString().toIntOrNull() ?: 15,
                    height      = binding.etHeight.text.toString().toIntOrNull() ?: 10,
                    length      = binding.etLength.text.toString().toIntOrNull() ?: 20,
                    images      = if (imageUrl.isNotEmpty()) listOf(imageUrl) else listOf("https://via.placeholder.com/300"),
                    categoryIds = catIds.ifEmpty { listOf(1L) }
                )
            )
        } else {
            viewModel.updateProduct(
                args.productId,
                UpdateProductRequest(
                    name        = name,
                    description = description,
                    price       = priceStr.toDouble(),
                    stock       = binding.etStock.text.toString().toIntOrNull(),
                    weight      = binding.etWeight.text.toString().toDoubleOrNull(),
                    width       = binding.etWidth.text.toString().toIntOrNull(),
                    height      = binding.etHeight.text.toString().toIntOrNull(),
                    length      = binding.etLength.text.toString().toIntOrNull(),
                    imageUrls   = if (imageUrl.isNotEmpty()) listOf(imageUrl) else null,
                    categoryIds = catIds.ifEmpty { null }
                )
            )
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
