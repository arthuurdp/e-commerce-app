package com.ecommerce.app.ui.customer.orders

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.R
import com.ecommerce.app.data.model.order.OrderResponse
import com.ecommerce.app.databinding.ItemOrderBinding
import com.ecommerce.app.util.formatDateTime
import com.ecommerce.app.util.toCurrency

class OrderAdapter(
    private val onItemClick: (OrderResponse) -> Unit
) : ListAdapter<OrderResponse, OrderAdapter.OrderViewHolder>(DiffCallback) {

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: OrderResponse) {
            binding.tvOrderId.text = "Pedido #${order.id}"
            binding.tvTotal.text = order.total.toCurrency()
            binding.tvDate.text = order.createdAt.formatDateTime()
            binding.tvItemCount.text = "${order.totalItems} ${if (order.totalItems == 1) "item" else "itens"}"

            val (labelRes, colorRes) = statusMeta(order.status)
            binding.tvStatus.text = binding.root.context.getString(labelRes)

            val color = ContextCompat.getColor(binding.root.context, colorRes)

            (binding.tvStatus.background as? GradientDrawable)?.setColor(color)

            binding.viewStatusBar.setBackgroundColor(color)

            binding.root.setOnClickListener { onItemClick(order) }
        }

        private fun statusMeta(status: String?): Pair<Int, Int> = when (status) {
            "PENDING" -> Pair(R.string.status_pending, R.color.gray)
            "PAID" -> Pair(R.string.status_paid, R.color.primary)
            "SHIPPED" -> Pair(R.string.status_shipped, android.R.color.holo_blue_dark)
            "DELIVERED" -> Pair(R.string.status_delivered, R.color.primary)
            "CANCELED" -> Pair(R.string.status_canceled, android.R.color.holo_red_dark)
            else -> Pair(R.string.status_pending, R.color.gray)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder(
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OrderResponse>() {
        override fun areItemsTheSame(old: OrderResponse, new: OrderResponse) = old.id == new.id
        override fun areContentsTheSame(old: OrderResponse, new: OrderResponse) = old == new
    }
}