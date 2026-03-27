package com.ecommerce.app.ui.customer.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecommerce.app.R
import com.ecommerce.app.data.model.cart.CartItemResponse
import com.ecommerce.app.databinding.ItemCartBinding
import com.ecommerce.app.util.toCurrency

class CartItemAdapter(
    private val onIncrement: (Long) -> Unit,
    private val onDecrement: (Long) -> Unit
) : ListAdapter<CartItemResponse, CartItemAdapter.CartViewHolder>(DiffCallback) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItemResponse) {
            binding.tvProductName.text = item.name
            binding.tvPrice.text = item.price.toCurrency()
            binding.tvQuantity.text = item.quantity.toString()
            binding.tvSubtotal.text = item.subtotal.toCurrency()

            Glide.with(binding.ivProduct)
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .centerCrop()
                .into(binding.ivProduct)

            binding.btnIncrement.setOnClickListener { onIncrement(item.productId) }
            binding.btnDecrement.setOnClickListener { onDecrement(item.productId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CartItemResponse>() {
        override fun areItemsTheSame(old: CartItemResponse, new: CartItemResponse) =
            old.productId == new.productId
        override fun areContentsTheSame(old: CartItemResponse, new: CartItemResponse) = old == new
    }
}
