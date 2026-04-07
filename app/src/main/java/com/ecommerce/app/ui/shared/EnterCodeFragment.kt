package com.ecommerce.app.ui.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentEnterCodeBinding
import com.ecommerce.app.ui.auth.AuthViewModel
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterCodeFragment : Fragment() {

    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()
    private val securityViewModel: SecurityViewModel by viewModels()
    private val args: EnterCodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeState()
    }

    private fun setupUI() {
        binding.mainContainer.setOnClickListener { hideKeyboard() }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        if (args.email.isNotEmpty()) {
            binding.tvSubtitle.text = getString(R.string.forgot_password_subtitle, args.email)
        }

        binding.etEnterCode.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilEnterCode, null)
        }

        binding.btnSendCode.setOnClickListener {
            hideKeyboard()
            val code = binding.etEnterCode.text.toString().trim()

            if (code.length == 6) {
                when (args.mode) {
                    "forgot_password" -> authViewModel.verifyResetCode(code)
                    "verify_email" -> securityViewModel.confirmEmail(code)
                    "change_email" -> securityViewModel.confirmEmailChange(code)
                    "change_password" -> securityViewModel.confirmPasswordChange(code)
                }
            } else {
                setFieldError(requireContext(), binding.tilEnterCode, "Enter the 6-digit code")
            }
        }
    }

    private fun observeState() {
        when (args.mode) {
            "forgot_password" -> {
                authViewModel.verifyResetCodeState.observe(viewLifecycleOwner) { result ->
                    handleResult(result)
                }
            }
            "verify_email" -> {
                securityViewModel.confirmEmailState.observe(viewLifecycleOwner) { result ->
                    handleResult(result)
                }
            }
            "change_email" -> {
                securityViewModel.confirmEmailChangeState.observe(viewLifecycleOwner) { result ->
                    handleResult(result)
                }
            }
            "change_password" -> {
                securityViewModel.confirmPasswordChangeState.observe(viewLifecycleOwner) { result ->
                    handleResult(result)
                }
            }
        }
    }

    private fun handleResult(result: NetworkResult<String>) {
        when (result) {
            is NetworkResult.Loading -> {
                binding.progressBar.show()
                binding.btnSendCode.isEnabled = false
            }

            is NetworkResult.Success -> {
                binding.progressBar.hide()
                binding.btnSendCode.isEnabled = true

                val code = binding.etEnterCode.text.toString().trim()

                when (args.mode) {
                    "forgot_password" -> {
                        val action = EnterCodeFragmentDirections.actionEnterCodeFragmentToResetPasswordFragment(args.email, code)
                        findNavController().navigate(action)
                    }

                    "verify_email" -> {
                        showToast("Email verified successfully!")
                        findNavController().popBackStack()
                    }

                    "change_email", "change_password" -> {
                        val message = if (args.mode == "change_email")
                            "Email changed! Please login again with your new email."
                        else
                            "Password changed! Please login again with your new password."

                        showToast(message)

                        authViewModel.logout()

                        val navOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.nav_graph, true)
                            .build()

                        findNavController().navigate(R.id.loginFragment, null, navOptions)
                    }

                    else -> findNavController().popBackStack()
                }
            }

            is NetworkResult.Error -> {
                binding.progressBar.hide()
                binding.btnSendCode.isEnabled = true
                setFieldError(requireContext(), binding.tilEnterCode, result.message)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
