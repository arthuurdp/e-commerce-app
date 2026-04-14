package com.ecommerce.app.ui.customer.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecommerce.app.R
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.databinding.ItemCommentBinding


class CommentAdapter(
    private val currentUserId: Long?,
    private val onDelete: (CommentResponse) -> Unit
) : ListAdapter<CommentResponse, CommentAdapter.CommentViewHolder>(DiffCallback) {

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentResponse) {
            binding.tvUserName.text = comment.userName
            binding.tvContent.text = comment.content

            binding.tvDate.text = try {
                val datePart = comment.createdAt.take(10)
                val parts = datePart.split("-")
                if (parts.size == 3) "${parts[2]}/${parts[1]}/${parts[0]}" else datePart
            } catch (e: Exception) {
                comment.createdAt.take(10)
            }

            if (!comment.userProfilePicture.isNullOrBlank()) {
                Glide.with(binding.ivAvatar)
                    .load(comment.userProfilePicture)
                    .placeholder(R.drawable.img_male)
                    .error(R.drawable.img_male)
                    .into(binding.ivAvatar)
            } else {
                binding.ivAvatar.setImageResource(R.drawable.img_male)
            }

            if (currentUserId != null && comment.userId == currentUserId) {
                binding.btnDelete.visibility = View.VISIBLE
                binding.btnDelete.setOnClickListener { onDelete(comment) }
            } else {
                binding.btnDelete.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CommentResponse>() {
        override fun areItemsTheSame(old: CommentResponse, new: CommentResponse) = old.id == new.id
        override fun areContentsTheSame(old: CommentResponse, new: CommentResponse) = old == new
    }
}