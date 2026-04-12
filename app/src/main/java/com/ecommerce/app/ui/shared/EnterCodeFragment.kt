package com.ecommerce.app.ui.shared

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
import java.util.Locale

@AndroidEntryPoint
class EnterCodeFragment : Fragment() {

    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()
    private val securityViewModel: SecurityViewModel by viewModels()
    private val mode: String by lazy { arguments?.getString("mode") ?: "none" }
    private val email: String by lazy { arguments?.getString("email") ?: "" }

    private var countDownTimer: CountDownTimer? = null
    private val timerDuration = 60000L

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
        startResendTimer()
        setupOnBackPressed()
    }

    private fun setupUI() {
        binding.mainContainer.setOnClickListener { hideKeyboard() }
        binding.btnBack.setOnClickListener { navigateBack() }

        if (email.isNotEmpty()) {
            binding.tvSubtitle.text = getString(R.string.forgot_password_subtitle, email)
        }

        binding.etEnterCode.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilEnterCode, null)
        }

        binding.btnSendCode.setOnClickListener {
            hideKeyboard()
            val code = binding.etEnterCode.text.toString().trim()

            if (code.length == 6) {
                when (mode) {
                    "forgot_password" -> authViewModel.verifyResetCode(code)
                    "verify_email" -> securityViewModel.confirmEmail(code)
                    "change_email" -> securityViewModel.confirmEmailChange(code)
                    "change_password" -> securityViewModel.confirmPasswordChange(code)
                }
            } else {
                setFieldError(requireContext(), binding.tilEnterCode, "Enter the 6-digit code")
            }
        }

        binding.tvResendCode.setOnClickListener { resendCode() }
    }

    private fun setupOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() { navigateBack() }
            }
        )
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun resendCode() {
        when (mode) {
            "forgot_password" -> authViewModel.forgotPassword(email)
            "verify_email" -> securityViewModel.sendEmailVerification()
            "change_email" -> securityViewModel.requestEmailChange(email)
            "change_password" -> {
                showToast("Please go back and re-enter your new password to resend.")
                return
            }
        }
        startResendTimer()
        showToast("Code resent to $email")
    }

    private fun startResendTimer() {
        binding.tvResendCode.hide()
        binding.tvTimer.show()

        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvTimer.text = String.format(
                    Locale.getDefault(), "Reenviar em 00:%02d", secondsRemaining
                )
            }
            override fun onFinish() {
                binding.tvTimer.hide()
                binding.tvResendCode.show()
            }
        }.start()
    }

    private fun observeState() {
        when (mode) {
            "forgot_password" -> {
                authViewModel.verifyResetCodeState.observe(viewLifecycleOwner) { handleResult(it) }
                authViewModel.forgotPasswordState.observe(viewLifecycleOwner) { }
            }
            "verify_email" -> {
                securityViewModel.confirmEmailState.observe(viewLifecycleOwner) { handleResult(it) }
                securityViewModel.sendEmailVerificationState.observe(viewLifecycleOwner) { }
            }
            "change_email" -> {
                securityViewModel.confirmEmailChangeState.observe(viewLifecycleOwner) { handleResult(it) }
                securityViewModel.requestEmailChangeState.observe(viewLifecycleOwner) { }
            }
            "change_password" -> {
                securityViewModel.confirmPasswordChangeState.observe(viewLifecycleOwner) { handleResult(it) }
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

                when (mode) {
                    "forgot_password" -> {
                        findNavController().navigate(
                            R.id.action_enterCodeFragment_to_resetPasswordFragment,
                            Bundle().apply {
                                putString("email", email)
                                putString("code", code)
                            }
                        )
                    }

                    "verify_email" -> {
                        showToast("Email verified successfully!")
                        findNavController().popBackStack()
                    }

                    "change_email", "change_password" -> {
                        val message = if (mode == "change_email") "Email changed! Please login again with your new email."
                        else "Password changed! Please login again with your new password."

                        showToast(message)
                        authViewModel.logout()

                        findNavController().popBackStack(
                            findNavController().graph.startDestinationId,
                            false
                        )
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
        countDownTimer?.cancel()
        _binding = null
    }
}