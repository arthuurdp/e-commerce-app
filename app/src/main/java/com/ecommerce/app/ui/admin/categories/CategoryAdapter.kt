package com.ecommerce.app.ui.admin.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.databinding.ItemCategoryBinding

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