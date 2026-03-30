package com.ecommerce.app.ui.customer.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentEditProfileBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        loadProfile()
        observeUpdate()

        binding.btnSave.setOnClickListener {
            viewModel.updateProfile(
                firstName = binding.etFirstName.text.toString().trim().ifEmpty { null },
                lastName = binding.etLastName.text.toString().trim().ifEmpty { null },
                phone = binding.etPhone.text.toString().trim().ifEmpty { null }
            )
        }

        binding.btnChangeEmail.setOnClickListener {
            findNavController().navigate(R.id.action_editProfileFragment_to_changeEmailFragment)
        }

        binding.btnChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_editProfileFragment_to_changePasswordFragment)
        }
    }

    private fun loadProfile() {
        viewModel.loadProfile()
        viewModel.profileState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val user = result.data
                binding.etFirstName.setText(user.firstName)
                binding.etLastName.setText(user.lastName)
                binding.etPhone.setText(user.phone)
                binding.tvCurrentEmail.text = user.email
            }
        }
    }

    private fun observeUpdate() {
        viewModel.updateState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnSave.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}