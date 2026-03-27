package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentAddressListBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
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