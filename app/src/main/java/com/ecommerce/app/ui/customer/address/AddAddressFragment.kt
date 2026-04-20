package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.model.address.CreateAddressRequest
import com.ecommerce.app.databinding.FragmentAddAddressBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
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

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.btnSave.setOnClickListener {
            attemptAddAddress()
            hideKeyboard()
        }

        setupCepField()
        setupErrorClearing()
        observeCepLookup()
        observeSaveState()
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

    private fun setupErrorClearing() {
        binding.etPostalCode.doAfterTextChanged { setFieldError(requireContext(), binding.tilPostalCode, null) }
        binding.etName.doAfterTextChanged { setFieldError(requireContext(), binding.tilLabel, null) }
        binding.etNumber.doAfterTextChanged { setFieldError(requireContext(), binding.tilNumber, null) }
        binding.etStreet.doAfterTextChanged { setFieldError(requireContext(), binding.tilStreet, null) }
        binding.etNeighborhood.doAfterTextChanged { setFieldError(requireContext(), binding.tilNeighborhood, null) }
        binding.etComplement.doAfterTextChanged { setFieldError(requireContext(), binding.tilComplement, null) }
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
                    binding.etCity.isEnabled = false
                    binding.etUf.isEnabled = false

                    if (binding.etStreet.text.isNullOrEmpty())
                        binding.etStreet.setText(cep.street ?: "")
                    if (binding.etNeighborhood.text.isNullOrEmpty())
                        binding.etNeighborhood.setText(cep.neighborhood ?: "")
                }

                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.etCity.setText("")
                    binding.etUf.setText("")
                    setFieldError(requireContext(), binding.tilPostalCode, "Invalid postal code")
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
                    findNavController().navigateUp()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
                }
            }
        }
    }

    private fun attemptAddAddress() {
            val name = binding.etName.text.toString().trim().ifEmpty { "Meu Endereço" }
            val postalCode = binding.etPostalCode.text.toString().replace("-", "").trim()
            val street = binding.etStreet.text.toString().trim()
            val numberStr = binding.etNumber.text.toString().trim()
            val complement = binding.etComplement.text.toString().trim()
            val neighborhood = binding.etNeighborhood.text.toString().trim()

            var isValid = true

            if (postalCode.length != 8) {
                setFieldError(requireContext(), binding.tilPostalCode, "CEP inválido")
                isValid = false
            }

            if (street.isEmpty()) {
                setFieldError(requireContext(), binding.tilStreet, "Rua é obrigatória")
                isValid = false
            }

            val number = numberStr.toIntOrNull()
            if (number == null) {
                setFieldError(requireContext(), binding.tilNumber, "Número inválido")
                isValid = false
            }

            if (neighborhood.isEmpty()) {
                setFieldError(requireContext(), binding.tilNeighborhood, "Bairro é obrigatório")
                isValid = false
            }

            if (complement.isEmpty()) {
                setFieldError(requireContext(), binding.tilComplement, "Complemento é obrigatório")
                isValid = false
            }

            if (!isValid) return

            viewModel.saveAddress(
                CreateAddressRequest(
                    name = name,
                    street = street,
                    number = number!!,
                    complement = complement,
                    neighborhood = neighborhood,
                    postalCode = postalCode,
                )
            )
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}