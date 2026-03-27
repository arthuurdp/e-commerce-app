package com.ecommerce.app.ui.customer.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.databinding.ItemOrderLineBinding
import com.ecommerce.app.util.toCurrency

class OrderItemAdapter : ListAdapter<OrderItemResponse, OrderItemAdapter.VH>(Diff) {

    inner class VH(val binding: ItemOrderLineBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemOrderLineBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.binding.tvProductName.text = item.productName
        holder.binding.tvQty.text         = "× ${item.quantity}"
        holder.binding.tvUnitPrice.text   = item.unitPrice.toCurrency()
        holder.binding.tvSubtotal.text    = item.subtotal.toCurrency()
    }

    object Diff : DiffUtil.ItemCallback<OrderItemResponse>() {
        override fun areItemsTheSame(a: OrderItemResponse, b: OrderItemResponse) =
            a.productId == b.productId
        override fun areContentsTheSame(a: OrderItemResponse, b: OrderItemResponse) = a == b
    }
}