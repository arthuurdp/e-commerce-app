package com.ecommerce.app.ui.customer.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.databinding.FragmentEditProfileBinding
import com.ecommerce.app.databinding.FragmentSecurityBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProfile()
        viewModel.profileState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                binding.etFirstName.setText(result.data.firstName)
                binding.etLastName.setText(result.data.lastName)
                binding.etPhone.setText(result.data.phone)
            }
        }

        viewModel.updateState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> { binding.progressBar.show(); binding.btnSave.isEnabled = false }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Profile updated!")
                    findNavController().navigateUp()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
                    showToast(result.message)
                }
            }
        }

        binding.btnSave.setOnClickListener {
            viewModel.updateProfile(
                firstName = binding.etFirstName.text.toString().trim().ifEmpty { null },
                lastName  = binding.etLastName.text.toString().trim().ifEmpty { null },
                phone     = binding.etPhone.text.toString().trim().ifEmpty { null }
            )
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}