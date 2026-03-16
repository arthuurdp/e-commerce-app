package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.R
import com.ecommerce.app.data.model.AddressResponse
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.databinding.FragmentAddressListBinding
import com.ecommerce.app.databinding.ItemAddressBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ── ViewModel ─────────────────────────────────────────────────────────────────

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _addressesState = MutableLiveData<NetworkResult<PageResponse<AddressResponse>>>()
    val addressesState: LiveData<NetworkResult<PageResponse<AddressResponse>>> = _addressesState

    private val _deleteState = MutableLiveData<NetworkResult<Unit>>()
    val deleteState: LiveData<NetworkResult<Unit>> = _deleteState

    fun loadAddresses() {
        viewModelScope.launch {
            _addressesState.value = NetworkResult.Loading
            _addressesState.value = addressRepository.getAddresses()
        }
    }

    fun deleteAddress(id: Long) {
        viewModelScope.launch {
            _deleteState.value = NetworkResult.Loading
            _deleteState.value = addressRepository.deleteAddress(id)
        }
    }
}

// ── Fragment ──────────────────────────────────────────────────────────────────

@AndroidEntryPoint
class AddressListFragment : Fragment() {

    private var _binding: FragmentAddressListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddressViewModel by viewModels()
    private lateinit var adapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AddressAdapter(
            onDelete = { address ->
                viewModel.deleteAddress(address.id)
            }
        )
        binding.rvAddresses.adapter = adapter

        binding.fabAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_addressListFragment_to_addAddressFragment)
        }

        viewModel.addressesState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(result.data.content)
                    binding.tvEmpty.visibility =
                        if (result.data.content.isEmpty()) View.VISIBLE else View.GONE
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }

        viewModel.deleteState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                showToast("Address deleted")
                viewModel.loadAddresses()
            } else if (result is NetworkResult.Error) {
                showToast(result.message)
            }
        }

        viewModel.loadAddresses()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// ── Adapter ───────────────────────────────────────────────────────────────────

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
