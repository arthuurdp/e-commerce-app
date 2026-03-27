package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.databinding.FragmentAddAddressBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.address.CepLookupResponse
import com.ecommerce.app.data.model.address.CreateAddressRequest
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ── ViewModel ─────────────────────────────────────────────────────────────────



// ── Fragment ──────────────────────────────────────────────────────────────────

@AndroidEntryPoint
class AddAddressFragment : Fragment() {

    private var _binding: FragmentAddAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddAddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Auto-lookup when CEP has 8 digits
        binding.etPostalCode.doAfterTextChanged { text ->
            val clean = text.toString().replace("-", "").trim()
            if (clean.length == 8) viewModel.lookupCep(clean)
        }

        viewModel.cepState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val cep = result.data
                    if (binding.etStreet.text.isNullOrEmpty())
                        binding.etStreet.setText(cep.street ?: "")
                    if (binding.etNeighborhood.text.isNullOrEmpty())
                        binding.etNeighborhood.setText(cep.neighborhood ?: "")
                    binding.tvCity.text = "${cep.cityName} / ${cep.stateUf}"
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast("CEP not found")
                }
            }
        }

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
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val name         = binding.etName.text.toString().trim()
            val street       = binding.etStreet.text.toString().trim().ifEmpty { null }
            val numberStr    = binding.etNumber.text.toString().trim()
            val complement   = binding.etComplement.text.toString().trim().ifEmpty { null }
            val neighborhood = binding.etNeighborhood.text.toString().trim().ifEmpty { null }
            val postalCode   = binding.etPostalCode.text.toString().replace("-", "").trim()

            if (name.isEmpty() || numberStr.isEmpty() || postalCode.length < 8) {
                showToast("Please fill in Name, Number and Postal Code")
                return@setOnClickListener
            }

            viewModel.saveAddress(
                CreateAddressRequest(
                    name         = name,
                    street       = street,
                    number       = numberStr.toInt(),
                    complement   = complement,
                    neighborhood = neighborhood,
                    postalCode   = postalCode
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
