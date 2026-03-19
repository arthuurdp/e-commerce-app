package com.ecommerce.app.ui.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentForgotPasswordResetPasswordBinding
import com.ecommerce.app.ui.auth.AuthViewModel
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordResetPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordResetPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()
    private val args: ForgotPasswordResetPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResetPassword.setOnClickListener {
            val newPassword = binding.etNewPassword.text.toString().trim()
            if (newPassword.isNotEmpty()) {
                viewModel.resetPassword(args.code, newPassword)
            } else {
                showToast("Please enter a new password")
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.resetPasswordState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.btnResetPassword.isEnabled = false
                is NetworkResult.Success -> {
                    showToast("Password changed successfully!")
                    findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
                }
                is NetworkResult.Error -> {
                    binding.btnResetPassword.isEnabled = true
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
