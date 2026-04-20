package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentAddressListBinding
import com.ecommerce.app.util.*
import dagger.hilt.android.AndroidEntryPoint

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

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter = AddressAdapter(
            onEdit = { address ->
                findNavController().navigate(
                    R.id.action_addressListFragment_to_editAddressFragment,
                    Bundle().apply {
                        putParcelable("address", address)
                    }
                )
            },
            onDelete = { address -> viewModel.deleteAddress(address.id) }
        )

        binding.fabAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_addressListFragment_to_addAddressFragment)
        }

        setupRecyclerView()
        observeAddresses()
        observeDelete()
        viewModel.loadAddresses()
    }

    private fun observeAddresses() {
        viewModel.addressesState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.tvEmptyAddresses.hide()
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val items = result.data.content
                    adapter.submitList(items)
                    if (items.isEmpty()) {
                        binding.tvEmptyAddresses.show()
                        binding.rvAddresses.hide()
                    } else {
                        binding.tvEmptyAddresses.hide()
                        binding.rvAddresses.show()
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.tvEmptyAddresses.show()
                    binding.rvAddresses.hide()
                }
            }
        }
    }

    private fun observeDelete() {
        viewModel.deleteState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    viewModel.loadAddresses()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvAddresses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@AddressListFragment.adapter
            addDivider()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
