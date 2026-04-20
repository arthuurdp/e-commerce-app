package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.R
import com.ecommerce.app.data.model.notification.NotificationResponse
import com.ecommerce.app.databinding.ItemNotificationBinding
import com.ecommerce.app.util.formatDateTime

class NotificationsAdapter(
    private val onItemClick: (NotificationResponse) -> Unit
) : ListAdapter<NotificationResponse, NotificationsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NotificationResponse, onItemClick: (NotificationResponse) -> Unit) {
            binding.root.setOnClickListener { onItemClick(item) }
            binding.tvNotificationContent.text = item.message.orEmpty()

            val imageRes = when (item.type?.lowercase().orEmpty()) {
                "review" -> R.drawable.img_notification_review
                "comment" -> R.drawable.img_notification_comment
                "favorite" -> R.drawable.img_notification_favorite
                else -> R.drawable.img_notification_comment
            }
            binding.ivNotificationImage.setImageResource(imageRes)

            binding.tvNotificationDate.text = item.createdAt.formatDateTime()

            val isUnread = item.read == false
            binding.viewUnreadDot.visibility = if (isUnread) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NotificationResponse>() {
        override fun areItemsTheSame(old: NotificationResponse, new: NotificationResponse) = old.id == new.id
        override fun areContentsTheSame(old: NotificationResponse, new: NotificationResponse) = old == new
    }
}
