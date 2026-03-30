package com.ecommerce.app.ui.customer.profile.security

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecommerce.app.databinding.FragmentChangePasswordEnterNewPasswordBinding
import com.ecommerce.app.util.setFieldError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordEnterNewPasswordFragment : Fragment() {
    private val viewModel: SecurityViewModel by viewModels()
    private var _binding: FragmentChangePasswordEnterNewPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChangePasswordEnterNewPasswordBinding.bind(view)

        binding.btnResetPassword.setOnClickListener {
            attemptSendCode()
        }
    }

    private fun attemptSendCode() {
        val password = binding.etNewPassword.text.toString().trim()
        setFieldError(requireContext(), binding.tilNewPassword, null)

        if (password.isEmpty()) {
            setFieldError(requireContext(), binding.tilNewPassword, "Password cannot be empty")
            return
        }

        viewModel.requestPasswordChange(password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}