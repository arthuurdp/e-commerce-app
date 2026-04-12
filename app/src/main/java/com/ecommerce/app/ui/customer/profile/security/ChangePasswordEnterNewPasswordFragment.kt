package com.ecommerce.app.ui.customer.profile.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentChangePasswordEnterNewPasswordBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordEnterNewPasswordFragment : Fragment() {

    private val viewModel: SecurityViewModel by viewModels()
    private var _binding: FragmentChangePasswordEnterNewPasswordBinding? = null
    private val binding get() = _binding!!
    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordEnterNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainContainer.setOnClickListener { hideKeyboard() }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSendCode.setOnClickListener {
            attemptSendCode()
            hideKeyboard()
        }

        viewModel.userEmail.observe(viewLifecycleOwner) { email ->
            userEmail = email
        }

        observeState()
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

    private fun observeState() {
        viewModel.requestPasswordChangeState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.btnSendCode.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.btnSendCode.isEnabled = true

                    findNavController().navigate(
                        R.id.action_productDetailFragment_to_enterCodeFragment,
                        Bundle().apply {
                            putString("mode", "change_password")
                            putString("email", userEmail ?: "")
                        }
                    )
                }
                is NetworkResult.Error -> {
                    binding.btnSendCode.isEnabled = true
                    val msg = result.fieldErrors?.get("newPassword") ?: result.message
                    setFieldError(requireContext(), binding.tilNewPassword, msg)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
