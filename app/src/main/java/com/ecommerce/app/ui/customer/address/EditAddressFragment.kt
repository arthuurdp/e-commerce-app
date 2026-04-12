package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.data.model.address.AddressResponse
import com.ecommerce.app.data.model.address.UpdateAddressRequest
import com.ecommerce.app.databinding.FragmentEditAddressBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.getParcelableCompat
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAddressFragment : Fragment() {

    private var _binding: FragmentEditAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddressViewModel by viewModels()

    private val address: AddressResponse by lazy {
        requireArguments().getParcelableCompat("address", AddressResponse::class.java)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(address)
        observeViewModel()
    }

    private fun setupUI(address: AddressResponse) {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.etName.setText(address.name)
        binding.etPostalCode.setText(address.postalCode ?: "")
        binding.etCity.setText(address.city.name)
        binding.etUf.setText(address.state.uf)
        binding.etStreet.setText(address.street)
        binding.etNumber.setText(address.number.toString())
        binding.etComplement.setText(address.complement ?: "")
        binding.etNeighborhood.setText(address.neighborhood)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val street = binding.etStreet.text.toString().trim()
            val numberStr = binding.etNumber.text.toString().trim()
            val neighborhood = binding.etNeighborhood.text.toString().trim()
            val complement = binding.etComplement.text.toString().trim()

            if (name.isEmpty() || street.isEmpty() || numberStr.isEmpty() || neighborhood.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.editAddress(
                address.id,
                UpdateAddressRequest(
                    name = name,
                    street = street,
                    number = numberStr.toIntOrNull(),
                    neighborhood = neighborhood,
                    complement = complement.ifEmpty { null }
                )
            )
        }
    }

    private fun observeViewModel() {
        viewModel.editState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    Toast.makeText(requireContext(), "Address updated successfully", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
