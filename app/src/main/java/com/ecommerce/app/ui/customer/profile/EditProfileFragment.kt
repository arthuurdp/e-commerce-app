package com.ecommerce.app.ui.customer.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.data.model.auth.RegisterRequest
import com.ecommerce.app.databinding.FragmentEditProfileBinding
import com.ecommerce.app.util.MaskWatcher
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import com.ecommerce.app.util.toApiDateOrNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()

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
        binding.btnChangeEmail.setOnClickListener { findNavController().navigate(R.id.action_editProfileFragment_to_changeEmailFragment) }
        binding.btnChangePassword.setOnClickListener { findNavController().navigate(R.id.action_editProfileFragment_to_changePasswordFragment) }

        binding.btnSave.setOnClickListener {
            attemptUpdate()
            hideKeyboard()
        }

        loadProfile()
        observeUpdate()
        setupMasks()
        setupErrorClearing()
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

    private fun attemptUpdate() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val phone = binding.etPhone.text.toString().filter { it.isDigit() }

        var isValid = true

        if (firstName.isEmpty()) {
            setFieldError(requireContext(), binding.tilName, "Nome é obrigatório")
            isValid = false
        } else {
            setFieldError(requireContext(), binding.tilName, null)
        }

        if (lastName.isEmpty()) {
            setFieldError(requireContext(), binding.tilNickname, "Sobrenome é obrigatório")
            isValid = false
        } else {
            setFieldError(requireContext(), binding.tilNickname, null)
        }

        if (phone.isEmpty()) {
            setFieldError(requireContext(), binding.tilPhone, "Telefone é obrigatório")
            isValid = false
        } else {
            setFieldError(requireContext(), binding.tilPhone, null)
        }

        if (!isValid) return

        viewModel.updateProfile(
            firstName = firstName,
            lastName = lastName,
            phone = phone
        )
    }

    private fun setupMasks() {
        binding.etPhone.addTextChangedListener(MaskWatcher("(##) #####-####"))
    }

    private fun setupErrorClearing() {
        binding.etFirstName.doAfterTextChanged { setFieldError(requireContext(), binding.tilName, null) }
        binding.etLastName.doAfterTextChanged { setFieldError(requireContext(), binding.tilNickname, null) }
        binding.etPhone.doAfterTextChanged { setFieldError(requireContext(), binding.tilPhone, null) }
    }

    private fun observeUpdate() {
        viewModel.updateState.observe(viewLifecycleOwner) { result ->
            result ?: return@observe
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnSave.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
                    showToast("Profile updated!")
                    viewModel.clearUpdateState()
                    findNavController().navigateUp()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnSave.isEnabled = true
                    showToast(result.message)
                    viewModel.clearUpdateState()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}