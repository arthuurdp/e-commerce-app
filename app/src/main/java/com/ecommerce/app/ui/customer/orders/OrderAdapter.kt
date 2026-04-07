package com.ecommerce.app.ui.customer.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.R
import com.ecommerce.app.data.model.order.OrderResponse
import com.ecommerce.app.databinding.ItemOrderBinding
import com.ecommerce.app.util.toCurrency

class OrderAdapter(
    private val onItemClick: (OrderResponse) -> Unit
) : ListAdapter<OrderResponse, OrderAdapter.OrderViewHolder>(DiffCallback) {

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: OrderResponse) {
            binding.tvOrderId.text = "Pedido #${order.id}"
            binding.tvStatus.text = when (order.status) {
                "PENDING" -> "PENDENTE"
                "PAID" -> "PAGO"
                "SHIPPED" -> "ENVIADO"
                "DELIVERED" -> "ENTREGUE"
                "CANCELED" -> "CANCELADO"
                else -> order.status
            }
            binding.tvTotal.text = order.total.toCurrency()
            binding.tvItemCount.text = "${order.totalItems} ${if (order.totalItems == 1) "item" else "itens"}"
            
            val rawDate = order.createdAt.take(10)
            binding.tvDate.text = try {
                val parts = rawDate.split("-")
                if (parts.size == 3) "${parts[2]}/${parts[1]}/${parts[0]}" else rawDate
            } catch (e: Exception) {
                rawDate
            }

            binding.root.setOnClickListener { onItemClick(order) }

            val colorRes = when (order.status) {
                "PAID", "DELIVERED" -> R.color.primary
                "CANCELED" -> android.R.color.holo_red_dark
                "SHIPPED" -> android.R.color.holo_blue_dark
                else -> R.color.gray
            }
            binding.tvStatus.setTextColor(binding.root.context.getColor(colorRes))
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