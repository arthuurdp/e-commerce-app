package com.ecommerce.app.ui.customer.home

import android.R.attr.onClick
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.databinding.ItemBannerBinding

data class BannerItem(
    @DrawableRes val imageRes: Int
)

class BannerAdapter(
    private val items: List<BannerItem>
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BannerItem) {
            binding.ivBanner.setImageResource(item.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}