package com.ecommerce.app.ui.customer.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.databinding.ItemProfileOptionBinding

class ProfileOptionsAdapter(private val onItemClick: (ProfileOption) -> Unit) :
    ListAdapter<ProfileOption, ProfileOptionsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemProfileOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProfileOption, onItemClick: (ProfileOption) -> Unit) {
            binding.ivIcon.setImageResource(item.icon)
            binding.tvTitle.text = item.title
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProfileOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProfileOption>() {
        override fun areItemsTheSame(oldItem: ProfileOption, newItem: ProfileOption): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProfileOption, newItem: ProfileOption): Boolean {
            return oldItem == newItem
        }
    }
}
