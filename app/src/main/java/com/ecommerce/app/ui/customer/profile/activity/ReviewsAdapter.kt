package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.ItemCommentBinding

class ReviewsAdapter(
    private val onItemClick: (ReviewResponse) -> Unit
) : ListAdapter<ReviewResponse, ReviewsAdapter.ReviewViewHolder>(DiffCallback) {

    inner class ReviewViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewResponse) {
            binding.tvUserName.text = "Avaliação - ${review.rating} ${if (review.rating == 1) "(1 estrela)" else "(${review.rating} estrelas)"}"
            binding.tvContent.text = review.comment?.content ?: "Sem comentário"
            binding.tvDate.text = try {
                val datePart = review.createdAt.take(10)
                val parts = datePart.split("-")
                if (parts.size == 3) "${parts[2]}/${parts[1]}/${parts[0]}" else datePart
            } catch (e: Exception) {
                review.createdAt.take(10)
            }
            
            binding.btnDelete.visibility = android.view.View.GONE
            binding.root.setOnClickListener { onItemClick(review) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ReviewResponse>() {
        override fun areItemsTheSame(old: ReviewResponse, new: ReviewResponse) = old.id == new.id
        override fun areContentsTheSame(old: ReviewResponse, new: ReviewResponse) = old == new
    }
}
