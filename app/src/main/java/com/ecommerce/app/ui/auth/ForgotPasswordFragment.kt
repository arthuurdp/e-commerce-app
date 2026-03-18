package com.ecommerce.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentForgotPasswordBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.setFieldError
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // Clear error as user types — only after they've tried submitting
        binding.etEmail.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilEmail, null)
        }

        binding.btnSendCode.setOnClickListener {
            binding.btnSendCode.setOnClickListener {
                attemptSendCode()
            }
        }

        binding.btnResetPassword.setOnClickListener {
            attemptResetPassword()
        }

        observeStates()
    }

    private fun attemptSendCode() {
        val email = binding.etEmail.text.toString().trim()

        setFieldError(requireContext(), binding.tilEmail, null)

        if (email.isEmpty()) {
            setFieldError(requireContext(), binding.tilEmail, "Enter your e-mail")
            return
        }

        viewModel.forgotPassword(email)
    }

    private fun attemptResetPassword() {
        val code = binding.etCode.text.toString().trim()
        val newPassword = binding.etNewPassword.text.toString()
        if (code.isEmpty() || newPassword.isEmpty()) {
            showToast("Fill in both fields")
            return
        }
        viewModel.resetPassword(code, newPassword)
    }

    private fun observeStates() {
        viewModel.forgotPasswordState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast(result.data)
                    binding.layoutReset.show()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }

        viewModel.resetPasswordState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Password changed successfully!")
                    findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
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