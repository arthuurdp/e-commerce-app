package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.model.address.CreateAddressRequest
import com.ecommerce.app.databinding.FragmentAddAddressBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAddressFragment : Fragment() {

    private var _binding: FragmentAddAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddAddressViewModel by viewModels()

    private var isApplyingMask = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        setupCepField()
        observeCepLookup()
        observeSaveState()
        setupSaveButton()
    }

    private fun setupCepField() {
        binding.etPostalCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isApplyingMask || s == null) return
                isApplyingMask = true

                val digits = s.toString().replace(Regex("\\D"), "").take(8)
                val masked = if (digits.length > 5) {
                    "${digits.substring(0, 5)}-${digits.substring(5)}"
                } else {
                    digits
                }

                if (s.toString() != masked) {
                    binding.etPostalCode.setText(masked)
                    binding.etPostalCode.setSelection(masked.length)
                }

                isApplyingMask = false

                if (digits.length == 8) viewModel.lookupCep(digits)
            }
        })
    }

    private fun observeCepLookup() {
        viewModel.cepState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()

                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val cep = result.data

                    binding.etCity.setText(cep.cityName)
                    binding.etUf.setText(cep.stateUf)

                    if (binding.etStreet.text.isNullOrEmpty())
                        binding.etStreet.setText(cep.street ?: "")
                    if (binding.etNeighborhood.text.isNullOrEmpty())
                        binding.etNeighborhood.setText(cep.neighborhood ?: "")
                }

                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.etCity.setText("")
                    binding.etUf.setText("")
                    showToast("CEP not found")
                }
            }
        }
    }

    private fun observeSaveState() {
        viewModel.saveState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnSave.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Address saved!")
                    findNavController().navigateUp()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
                    showToast("Failed to save address. Please try again.")
                }
            }
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val postalCode  = binding.etPostalCode.text.toString().replace("-", "").trim()
            val city = binding.etCity.text.toString().trim()
            val uf = binding.etUf.text.toString().trim()
            val street = binding.etStreet.text.toString().trim().ifEmpty { null }
            val numberStr = binding.etNumber.text.toString().trim()
            val complement = binding.etComplement.text.toString().trim().ifEmpty { null }
            val neighborhood = binding.etNeighborhood.text.toString().trim().ifEmpty { null }

            if (name.isEmpty()) {
                showToast("Please enter a label for this address")
                return@setOnClickListener
            }
            if (postalCode.length < 8) {
                showToast("Please enter a valid 8-digit postal code")
                return@setOnClickListener
            }
            if (city.isEmpty() || uf.isEmpty()) {
                showToast("City and state are required — check the postal code")
                return@setOnClickListener
            }
            if (numberStr.isEmpty()) {
                showToast("Please enter a street number")
                return@setOnClickListener
            }

            viewModel.saveAddress(
                CreateAddressRequest(
                    name = name,
                    street = street,
                    number = numberStr.toInt(),
                    complement = complement,
                    neighborhood = neighborhood,
                    postalCode = postalCode,
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}