package com.ecommerce.app.ui.admin.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.databinding.FragmentAdminCategoriesBinding
import com.ecommerce.app.databinding.ItemCategoryBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import androidx.appcompat.app.AlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminCategoriesFragment : Fragment() {
    private var _binding: FragmentAdminCategoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminCategoriesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdminCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryAdapter(onDelete = { cat ->
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Category")
                .setMessage("Delete \"${cat.name}\"?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(cat.id) }
                .setNegativeButton("Cancel", null)
                .show()
        })
        binding.rvCategories.adapter = adapter

        binding.fabAddCategory.setOnClickListener {
            val input = android.widget.EditText(requireContext()).apply {
                hint = "Category name"
            }
            AlertDialog.Builder(requireContext())
                .setTitle("New Category")
                .setView(input)
                .setPositiveButton("Create") { _, _ ->
                    val name = input.text.toString().trim()
                    if (name.isNotEmpty()) viewModel.create(name)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        viewModel.state.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(result.data.content)
                }
                is NetworkResult.Error -> { binding.progressBar.hide(); showToast(result.message) }
            }
        }

        viewModel.actionState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> { showToast("Done!"); viewModel.load() }
                is NetworkResult.Error -> showToast(result.message)
                else -> Unit
            }
        }

        viewModel.load()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
