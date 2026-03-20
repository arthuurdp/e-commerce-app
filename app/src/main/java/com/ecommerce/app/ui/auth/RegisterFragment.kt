package com.ecommerce.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.data.model.RegisterRequest
import com.ecommerce.app.databinding.FragmentRegisterBinding
import com.ecommerce.app.util.MaskWatcher
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainContainer.setOnClickListener { hideKeyboard() }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.etCpf.addTextChangedListener(MaskWatcher("###.###.###-##"))
        binding.etPhone.addTextChangedListener(MaskWatcher("(##) #####-####"))
        binding.etBirthDate.addTextChangedListener(MaskWatcher("####-##-##"))

        binding.etFirstName.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilFirstName, null)
        }
        binding.etLastName.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilLastName, null)
        }
        binding.etEmail.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilEmail, null)
        }
        binding.etPassword.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilPassword, null)
        }
        binding.etCpf.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilCpf, null)
        }
        binding.etPhone.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilPhone, null)
        }
        binding.etBirthDate.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilBirthDate, null)
        }

        val genders = listOf("Male", "Female", "Prefer not to say")
        binding.spinnerGender.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, genders
        )

        binding.btnRegister.setOnClickListener { attemptRegister() }
        binding.tvLogin.setOnClickListener { findNavController().navigateUp() }

        observeRegister()
    }

    private fun attemptRegister() {
        binding.tilFirstName.error = null
        binding.tilLastName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilCpf.error = null
        binding.tilPhone.error = null
        binding.tilBirthDate.error = null

        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val cpf = binding.etCpf.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val birthDate = binding.etBirthDate.text.toString().trim()
        val gender = binding.spinnerGender.selectedItem.toString().uppercase()

        var hasError = false

        if (firstName.isEmpty()) {
            binding.tilFirstName.error = "First name is required"
            hasError = true
        }
        if (lastName.isEmpty()) {
            binding.tilLastName.error = "Last name is required"
            hasError = true
        }
        if (email.isEmpty()) {
            binding.tilEmail.error = "E-mail is required"
            hasError = true
        }
        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            hasError = true
        }
        if (cpf.isEmpty()) {
            binding.tilCpf.error = "CPF is required"
            hasError = true
        }
        if (phone.isEmpty()) {
            binding.tilPhone.error = "Phone is required"
            hasError = true
        }
        if (birthDate.isEmpty()) {
            binding.tilBirthDate.error = "Birth date is required"
            hasError = true
        }

        if (hasError) return

        viewModel.register(
            RegisterRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                cpf = cpf,
                phone = phone,
                birthDate = birthDate,
                gender = gender
            )
        )
    }

    private fun observeRegister() {
        viewModel.registerState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnRegister.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.btnRegister.isEnabled = true
                    showToast("Account created! Please log in.")
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnRegister.isEnabled = true

                    result.fieldErrors?.forEach { (field, message) ->
                        when (field) {
                            "firstName" -> binding.tilFirstName.error = message
                            "lastName" -> binding.tilLastName.error = message
                            "email" -> binding.tilEmail.error = message
                            "password" -> binding.tilPassword.error = message
                            "cpf" -> binding.tilCpf.error = message
                            "phone" -> binding.tilPhone.error = message
                            "birthDate" -> binding.tilBirthDate.error = message
                            "gender" -> showToast(message)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
