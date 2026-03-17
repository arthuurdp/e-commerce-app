package com.ecommerce.app.ui.auth

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentLoginBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCredential.doAfterTextChanged {
            setFieldError(binding.tilEmail, null)
        }
        binding.etPassword.doAfterTextChanged {
            setFieldError(binding.tilPassword, null)
        }

        binding.btnLogin.setOnClickListener { attemptLogin() }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        observeLogin()
    }

    private fun attemptLogin() {
        val credential = binding.etCredential.text.toString().trim()
        val password = binding.etPassword.text.toString()

        setFieldError(binding.tilEmail, null)
        setFieldError(binding.tilPassword, null)

        if (credential.isEmpty()) {
            setFieldError(binding.tilEmail, "Enter your email or CPF")
            return
        }
        if (password.isEmpty()) {
            setFieldError(binding.tilPassword, "Enter your password")
            return
        }
        viewModel.login(credential, password)
    }

    private fun observeLogin() {
        viewModel.loginState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnLogin.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.btnLogin.isEnabled = true

                    val destination = if (viewModel.isAdmin) {
                        R.id.action_loginFragment_to_admin_nav_graph
                    } else {
                        R.id.action_loginFragment_to_homeFragment
                    }
                    findNavController().navigate(destination)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnLogin.isEnabled = true
                    setFieldError(binding.tilEmail, result.message)
                    setFieldError(binding.tilPassword, result.message)
                }
            }
        }
    }

    private fun setFieldError(layout: TextInputLayout, message: String?) {
        val hasError = message != null

        if (hasError) {
            layout.isErrorEnabled = true
            layout.error = message
        } else {
            layout.error = null
            layout.isErrorEnabled = false
        }

        val color = if (hasError) {
            ContextCompat.getColor(requireContext(), R.color.red)
        } else {
            ContextCompat.getColor(requireContext(), R.color.purple)
        }

        layout.setStartIconTintList(ColorStateList.valueOf(color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
