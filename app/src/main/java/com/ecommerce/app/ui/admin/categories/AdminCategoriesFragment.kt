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
import com.ecommerce.app.data.model.CategoryResponse
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.repository.CategoryRepository
import com.ecommerce.app.databinding.FragmentAdminCategoriesBinding
import com.ecommerce.app.databinding.ItemCategoryBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _state = MutableLiveData<NetworkResult<PageResponse<CategoryResponse>>>()
    val state: LiveData<NetworkResult<PageResponse<CategoryResponse>>> = _state

    private val _actionState = MutableLiveData<NetworkResult<*>>()
    val actionState: LiveData<NetworkResult<*>> = _actionState

    fun load() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            _state.value = categoryRepository.getCategories()
        }
    }

    fun create(name: String) {
        viewModelScope.launch {
            _actionState.value = NetworkResult.Loading
            _actionState.value = categoryRepository.createCategory(name)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            _actionState.value = NetworkResult.Loading
            _actionState.value = categoryRepository.deleteCategory(id)
        }
    }
}

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

class CategoryAdapter(
    private val onDelete: (CategoryResponse) -> Unit
) : ListAdapter<CategoryResponse, CategoryAdapter.VH>(Diff) {

    inner class VH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val cat = getItem(position)
        holder.binding.tvCategoryName.text = cat.name
        holder.binding.btnDelete.setOnClickListener { onDelete(cat) }
    }

    object Diff : DiffUtil.ItemCallback<CategoryResponse>() {
        override fun areItemsTheSame(a: CategoryResponse, b: CategoryResponse) = a.id == b.id
        override fun areContentsTheSame(a: CategoryResponse, b: CategoryResponse) = a == b
    }
}
