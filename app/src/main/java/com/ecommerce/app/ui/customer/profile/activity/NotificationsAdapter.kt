package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.R
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.databinding.ItemNotificationBinding

class NotificationsAdapter : ListAdapter<NotificationResponse, NotificationsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotificationResponse) {
            binding.tvNotificationContent.text = item.message ?: ""
            
            val imageRes = when (item.type?.lowercase() ?: "") {
                "review" -> R.drawable.img_notification_review
                "comment" -> R.drawable.img_notification_comment
                "favorite" -> R.drawable.img_notification_favorite
                else -> R.drawable.img_notification_comment
            }
            binding.ivNotificationImage.setImageResource(imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotificationBinding.inflate(
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
