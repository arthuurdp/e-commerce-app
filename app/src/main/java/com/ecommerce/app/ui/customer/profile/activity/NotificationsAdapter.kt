package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.databinding.ItemCommentBinding

class NotificationsAdapter : ListAdapter<NotificationResponse, NotificationsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotificationResponse) {
            binding.tvUserName.text = "Sistema"
            binding.tvContent.text = item.message
            binding.tvDate.text = item.createdAt
            binding.btnDelete.visibility = android.view.View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NotificationResponse>() {
        override fun areItemsTheSame(oldItem: NotificationResponse, newItem: NotificationResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NotificationResponse, newItem: NotificationResponse): Boolean {
            return oldItem == newItem
        }
    }
}
