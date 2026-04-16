package com.ecommerce.app.ui.customer.profile.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.DialogReviewOptionsBinding
import com.ecommerce.app.databinding.ItemActivityReviewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

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

            binding.ibMore.setOnClickListener { showOptionsDialog(review) }
        }

        private fun showOptionsDialog(review: ReviewResponse) {
            val context = binding.root.context
            val dialog = BottomSheetDialog(context)
            val dialogBinding = DialogReviewOptionsBinding.inflate(LayoutInflater.from(context))

            dialogBinding.btnEdit.setOnClickListener { onEditClick(review); dialog.dismiss() }
            dialogBinding.btnDelete.setOnClickListener { onDeleteClick(review); dialog.dismiss() }
            dialogBinding.btnCancel.setOnClickListener { dialog.dismiss() }

            dialog.setContentView(dialogBinding.root)
            dialog.show()
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
