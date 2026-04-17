package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.ItemActivityReviewBinding
import com.ecommerce.app.util.DialogUtils

class ReviewsAdapter(
    private val onEditClick: (ReviewResponse) -> Unit,
    private val onDeleteClick: (ReviewResponse) -> Unit,
) : ListAdapter<ReviewResponse, ReviewsAdapter.ReviewViewHolder>(DiffCallback) {

    inner class ReviewViewHolder(private val binding: ItemActivityReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewResponse) {
            binding.tvUserName.text = review.userName
            binding.rbRating.rating = review.rating.toFloat()
            binding.tvComment.text = review.comment?.content ?: "Sem comentário"
            binding.tvDate.text = try {
                val parts = review.createdAt.take(10).split("-")
                if (parts.size == 3) "${parts[2]}/${parts[1]}/${parts[0]}" else review.createdAt.take(10)
            } catch (e: Exception) { review.createdAt.take(10) }

            if (review.userProfilePictureUrl != null) {
                Glide.with(binding.root.context)
                    .load(review.userProfilePictureUrl)
                    .signature(ObjectKey(System.currentTimeMillis() / (1000 * 60)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivAvatar)
            }

            binding.ibMore.setOnClickListener {
                DialogUtils.showOptionsDialog(
                    context = binding.root.context,
                    item = review,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewViewHolder(ItemActivityReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) = holder.bind(getItem(position))

    companion object DiffCallback : DiffUtil.ItemCallback<ReviewResponse>() {
        override fun areItemsTheSame(old: ReviewResponse, new: ReviewResponse) = old.id == new.id
        override fun areContentsTheSame(old: ReviewResponse, new: ReviewResponse) = old == new
    }
}
