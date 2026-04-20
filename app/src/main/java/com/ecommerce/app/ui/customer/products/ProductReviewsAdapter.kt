package com.ecommerce.app.ui.customer.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.ecommerce.app.BuildConfig
import com.ecommerce.app.R
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.ItemReviewBinding
import com.ecommerce.app.util.formatDateTime
import kotlin.text.startsWith

class ProductReviewsAdapter : ListAdapter<ReviewResponse, ProductReviewsAdapter.ReviewViewHolder>(DiffCallback) {

    inner class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewResponse) {
            binding.tvUserName.text = review.userName
            binding.rbRating.rating = review.rating.toFloat()
            binding.tvComment.text = review.comment?.content
            binding.tvDate.text = review.createdAt.formatDateTime()

            val rawUrl = review.userProfilePictureUrl

            if (!rawUrl.isNullOrBlank()) {
                val finalUrl = if (rawUrl.startsWith("http")) {
                    rawUrl
                } else {
                    "${BuildConfig.BASE_URL}/uploads/$rawUrl"
                }

                Glide.with(binding.ivAvatar)
                    .load(finalUrl)
                    .placeholder(R.drawable.img_male)
                    .error(R.drawable.img_male)
                    .into(binding.ivAvatar)
            } else {
                binding.ivAvatar.setImageResource(R.drawable.img_male)
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
