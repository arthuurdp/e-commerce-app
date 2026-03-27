package com.ecommerce.app.ui.admin.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.databinding.ItemUserBinding
import com.ecommerce.app.data.model.user.UserResponse

class UserAdapter : ListAdapter<UserResponse, UserAdapter.VH>(Diff) {
    inner class VH(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val user = getItem(position)
        holder.binding.tvName.text  = "${user.firstName} ${user.lastName}"
        holder.binding.tvEmail.text = user.email
        holder.binding.tvRole.text  = "USER"
    }

    object Diff : DiffUtil.ItemCallback<UserResponse>() {
        override fun areItemsTheSame(a: UserResponse, b: UserResponse) = a.id == b.id
        override fun areContentsTheSame(a: UserResponse, b: UserResponse) = a == b
    }
}