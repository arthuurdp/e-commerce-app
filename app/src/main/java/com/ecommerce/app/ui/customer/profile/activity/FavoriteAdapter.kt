package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecommerce.app.R
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.databinding.ItemFavoriteBinding
import com.ecommerce.app.util.toCurrency

class FavoriteAdapter(
    private val onItemClick: (ProductResponse) -> Unit,
    private val onRemoveClick: (ProductResponse) -> Unit
) : ListAdapter<ProductResponse, FavoriteAdapter.FavoriteViewHolder>(DiffCallback) {

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductResponse) {
            binding.tvProductName.text = product.name
            binding.tvProductDescription.text = product.description
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(old: ProductResponse, new: ProductResponse) = old.id == new.id
        override fun areContentsTheSame(old: ProductResponse, new: ProductResponse) = old == new
    }
}
