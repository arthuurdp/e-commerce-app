package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ecommerce.app.BuildConfig
import com.ecommerce.app.R
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.ItemActivityReviewBinding
import com.ecommerce.app.util.DialogUtils
import com.ecommerce.app.util.formatDate
import kotlin.text.isNullOrEmpty
import kotlin.text.startsWith
import kotlin.text.uppercase

class ReviewsAdapter(
    private val onEditClick: (ReviewResponse) -> Unit,
    private val onDeleteClick: (ReviewResponse) -> Unit,
    private val onSeeProductClick: (Long) -> Unit,
) : ListAdapter<ReviewResponse, ReviewsAdapter.ReviewViewHolder>(DiffCallback) {

    inner class ReviewViewHolder(private val binding: ItemActivityReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewResponse) {
            binding.tvProductName.text = review.productName?.uppercase() ?: "PRODUTO #${review.productId}"
            binding.rbRating.rating = review.rating.toFloat()
            binding.tvDate.text = review.createdAt.formatDate()

            val profileUrl = review.userProfilePictureUrl
            val finalUrl = if (profileUrl.isNullOrEmpty()) {
                null
            } else if (profileUrl.startsWith("http")) {
                profileUrl
            } else {
                "${BuildConfig.BASE_URL}/uploads/$profileUrl"
            }

            Glide.with(binding.root.context)
                .load(finalUrl)
                .placeholder(R.drawable.img_male)
                .error(R.drawable.img_male)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivAvatar)

            binding.tvComment.text = review.comment?.content ?: "Sem comentário"

            binding.ibMore.setOnClickListener {
                DialogUtils.showOptionsDialog(
                    context = binding.root.context,
                    item = review,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }

            binding.tvSeeProduct.setOnClickListener {
                onSeeProductClick(review.productId)
            }

            binding.root.setOnClickListener {
                onSeeProductClick(review.productId)
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