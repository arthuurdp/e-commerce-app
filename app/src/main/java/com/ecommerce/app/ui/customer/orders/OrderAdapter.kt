package com.ecommerce.app.ui.customer.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.order.OrderResponse
import com.ecommerce.app.databinding.ItemOrderBinding
import com.ecommerce.app.util.toCurrency

class OrderAdapter(
    private val onItemClick: (OrderResponse) -> Unit
) : ListAdapter<OrderResponse, OrderAdapter.OrderViewHolder>(DiffCallback) {

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: OrderResponse) {
            binding.tvOrderId.text = "#${order.id}"
            binding.tvStatus.text = order.status
            binding.tvTotal.text = order.total.toCurrency()
            binding.tvItemCount.text = "${order.totalItems} items"
            binding.tvDate.text = order.createdAt.take(10)
            binding.root.setOnClickListener { onItemClick(order) }

            val colorRes = when (order.status) {
                "PAID" -> android.R.color.holo_green_dark
                "CANCELED" -> android.R.color.holo_red_dark
                "SHIPPED",
                "DELIVERED" -> android.R.color.holo_blue_dark

                else -> android.R.color.darker_gray
            }
            binding.tvStatus.setTextColor(
                binding.root.context.getColor(colorRes)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OrderResponse>() {
        override fun areItemsTheSame(old: OrderResponse, new: OrderResponse) = old.id == new.id
        override fun areContentsTheSame(old: OrderResponse, new: OrderResponse) = old == new
    }
}