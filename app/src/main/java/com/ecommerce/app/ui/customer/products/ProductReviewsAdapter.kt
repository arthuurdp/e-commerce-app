package com.ecommerce.app.ui.customer.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.ItemReviewBinding

class ProductReviewsAdapter : ListAdapter<ReviewResponse, ProductReviewsAdapter.ReviewViewHolder>(DiffCallback) {

    inner class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewResponse) {
            binding.tvUserName.text = review.userName
            binding.rbRating.rating = review.rating.toFloat()
            binding.tvComment.text = review.comment?.content
            
            binding.tvDate.text = try {
                val datePart = review.createdAt.take(16).replace("T", " ")
                // Optionally format it further if needed, e.g., 2026-04-16 14:00
                datePart
            } catch (e: Exception) {
                review.createdAt.take(10)
            }

            if (review.userProfilePictureUrl != null) {
                Glide.with(binding.root.context)
                    .load(review.userProfilePictureUrl)
                    .signature(ObjectKey(System.currentTimeMillis() / (1000 * 60)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(
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
