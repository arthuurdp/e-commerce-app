package com.ecommerce.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.data.model.RegisterRequest
import com.ecommerce.app.databinding.FragmentRegisterBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
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

        // Gender spinner
        val genders = listOf("MALE", "FEMALE", "OTHER")
        binding.spinnerGender.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, genders
        )

        binding.btnRegister.setOnClickListener { attemptRegister() }
        binding.tvLogin.setOnClickListener { findNavController().navigateUp() }

        observeRegister()
    }

    private fun attemptRegister() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName  = binding.etLastName.text.toString().trim()
        val email     = binding.etEmail.text.toString().trim()
        val password  = binding.etPassword.text.toString()
        val cpf       = binding.etCpf.text.toString().trim()
        val phone     = binding.etPhone.text.toString().trim()
        val birthDate = binding.etBirthDate.text.toString().trim() // expects yyyy-MM-dd
        val gender    = binding.spinnerGender.selectedItem.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
            password.isEmpty() || cpf.isEmpty() || phone.isEmpty() || birthDate.isEmpty()
        ) {
            showToast("Please fill in all fields")
            return
        }

        viewModel.register(
            RegisterRequest(firstName, lastName, email, password, cpf, phone, birthDate, gender)
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
