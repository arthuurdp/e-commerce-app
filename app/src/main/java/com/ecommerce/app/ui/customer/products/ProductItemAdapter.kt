package com.ecommerce.app.ui.customer.products

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecommerce.app.data.model.product.ProductImageResponse

class ProductItemAdapter(
    private val imgs: List<ProductImageResponse>
) : RecyclerView.Adapter<ProductItemAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ImageViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.imageView)
            .load(imgs[position].url)
            .into(holder.imageView)
    }

    override fun getItemCount() = imgs.size
}