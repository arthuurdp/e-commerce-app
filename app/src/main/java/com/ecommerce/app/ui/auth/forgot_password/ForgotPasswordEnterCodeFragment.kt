package com.ecommerce.app.ui.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentForgotPasswordEnterCodeBinding
import com.ecommerce.app.ui.auth.AuthViewModel
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordEnterCodeFragment : Fragment() {

    private var _binding: FragmentForgotPasswordEnterCodeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()
    private val args: ForgotPasswordEnterCodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainContainer.setOnClickListener {
            hideKeyboard()
        }

        binding.tvSubtitle.text = getString(R.string.forgot_password_subtitle, args.email)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.etEnterCode.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilEnterCode, null)
        }

        binding.btnSendCode.setOnClickListener {
            attemptVerifyCode()
        }

        observeState()
    }

    private fun attemptVerifyCode() {
        val code = binding.etEnterCode.text.toString().trim()
        if (code.length == 6) {
            viewModel.verifyResetCode(code)
        } else {
            setFieldError(requireContext(), binding.tilEnterCode, "Enter the 6-digit code")
        }
    }

    private fun observeState() {
        viewModel.verifyResetCodeState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnSendCode.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.btnSendCode.isEnabled = true
                    val code = binding.etEnterCode.text.toString().trim()
                    val action = ForgotPasswordEnterCodeFragmentDirections.actionEnterCodeFragmentToResetPasswordFragment(args.email, code)
                    findNavController().navigate(action)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnSendCode.isEnabled = true

                    val errorMessage = result.fieldErrors?.get("code")
                        ?: result.fieldErrors?.get("message") 
                        ?: result.message

                    setFieldError(requireContext(), binding.tilEnterCode, errorMessage)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
