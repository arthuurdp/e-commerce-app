package com.ecommerce.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentLoginBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
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

        if (credential.isEmpty()) {
            binding.etCredential.error = "Enter your email or CPF"
            return
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Enter your password"
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
                    // Navigate based on role stored after login
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
