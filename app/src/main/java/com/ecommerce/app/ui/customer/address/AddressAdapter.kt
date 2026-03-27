package com.ecommerce.app.ui.customer.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.address.AddressResponse
import com.ecommerce.app.databinding.ItemAddressBinding

class AddressAdapter(
    private val onDelete: (AddressResponse) -> Unit
) : ListAdapter<AddressResponse, AddressAdapter.VH>(Diff) {

    inner class VH(val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val address = getItem(position)
        holder.binding.tvAddressName.text   = address.name
        holder.binding.tvAddressStreet.text =
            "${address.street}, ${address.number} - ${address.neighborhood}"
        holder.binding.tvAddressCity.text   =
            "${address.city.name} / ${address.state.uf} · ${address.postalCode}"
        holder.binding.btnDelete.setOnClickListener { onDelete(address) }
    }

    object Diff : DiffUtil.ItemCallback<AddressResponse>() {
        override fun areItemsTheSame(a: AddressResponse, b: AddressResponse) = a.id == b.id
        override fun areContentsTheSame(a: AddressResponse, b: AddressResponse) = a == b
    }
}