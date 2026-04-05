package com.ecommerce.app.ui.customer.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecommerce.app.R
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.databinding.ItemProductSearchBinding
import com.ecommerce.app.util.toCurrency

class ProductSearchAdapter(
    private val onItemClick: (ProductResponse) -> Unit
) : ListAdapter<ProductResponse, ProductSearchAdapter.SearchViewHolder>(DiffCallback) {

    inner class SearchViewHolder(private val binding: ItemProductSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductResponse) {
            binding.tvProductName.text = product.name
            binding.tvDescription.text = product.description
            binding.tvPrice.text = product.price.toCurrency()

            Glide.with(binding.ivProduct)
                .load(product.mainImage)
                .placeholder(R.drawable.img_logo)
                .error(R.drawable.img_logo)
                .centerCrop()
                .into(binding.ivProduct)

            binding.root.setOnClickListener { onItemClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemProductSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(old: ProductResponse, new: ProductResponse) = old.id == new.id
        override fun areContentsTheSame(old: ProductResponse, new: ProductResponse) = old == new
    }
}