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

        setupMasks()
        setupErrorClearing()

        val genders = listOf("Male", "Female", "Other")
        binding.spinnerGender.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, genders
        )

        binding.btnRegister.setOnClickListener { attemptRegister() }
        binding.tvLogin.setOnClickListener { findNavController().navigateUp() }

        observeRegister()
    }

    private fun setupErrorClearing() {
        binding.etFirstName.doAfterTextChanged { setFieldError(requireContext(), binding.tilFirstName, null) }
        binding.etLastName.doAfterTextChanged { setFieldError(requireContext(), binding.tilLastName, null) }
        binding.etEmail.doAfterTextChanged { setFieldError(requireContext(), binding.tilEmail, null) }
        binding.etPassword.doAfterTextChanged { setFieldError(requireContext(), binding.tilPassword, null) }
        binding.etCpf.doAfterTextChanged { setFieldError(requireContext(), binding.tilCpf, null) }
        binding.etPhone.doAfterTextChanged { setFieldError(requireContext(), binding.tilPhone, null) }
        binding.etBirthDate.doAfterTextChanged { setFieldError(requireContext(), binding.tilBirthDate, null) }
    }

    private fun setupMasks() {
        binding.etCpf.addTextChangedListener(MaskWatcher("###.###.###-##"))
        binding.etPhone.addTextChangedListener(MaskWatcher("(##) #####-####"))
        binding.etBirthDate.addTextChangedListener(MaskWatcher("####-##-##"))
    }

    private fun attemptRegister() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val cpf = binding.etCpf.text.toString().filter { it.isDigit() }
        val phone = binding.etPhone.text.toString().filter { it.isDigit() }
        val birthDate = binding.etBirthDate.text.toString().trim()
        val gender = binding.spinnerGender.selectedItem.toString().uppercase()

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

                    if (!result.fieldErrors.isNullOrEmpty()) {
                        result.fieldErrors.forEach { (field, message) ->
                            when (field) {
                                "firstName" -> setFieldError(requireContext(), binding.tilFirstName, message)
                                "lastName" -> setFieldError(requireContext(), binding.tilLastName, message)
                                "email" -> setFieldError(requireContext(), binding.tilEmail, message)
                                "password" -> setFieldError(requireContext(), binding.tilPassword, message)
                                "cpf" -> setFieldError(requireContext(), binding.tilCpf, message)
                                "phone" -> setFieldError(requireContext(), binding.tilPhone, message)
                                "birthDate" -> setFieldError(requireContext(), binding.tilBirthDate, message)
                                "gender" -> showToast(message)
                            }
                        }
                    } else {
                        val msg = result.message.lowercase()

                        if (msg.contains("email") || msg.contains("e-mail")) {
                            setFieldError(requireContext(), binding.tilEmail, result.message)
                        }
                        if (msg.contains("cpf")) {
                            setFieldError(requireContext(), binding.tilCpf, result.message)
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
