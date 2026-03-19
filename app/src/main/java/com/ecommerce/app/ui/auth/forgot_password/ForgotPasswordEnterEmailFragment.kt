package com.ecommerce.app.ui.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.databinding.FragmentForgotPasswordBinding
import com.ecommerce.app.ui.auth.AuthViewModel
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.setFieldError
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordEnterEmailFragment : Fragment() {

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

        binding.etEmail.doAfterTextChanged {
            setFieldError(requireContext(), binding.tilEmail, null)
        }

        binding.btnSendCode.setOnClickListener {
            attemptSendCode()
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

    private fun observeStates() {
        viewModel.forgotPasswordState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val email = binding.etEmail.text.toString().trim()
                    val action = ForgotPasswordEnterEmailFragmentDirections.actionForgotPasswordFragmentToEnterCodeFragment(email)
                    findNavController().navigate(action)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()

                    val errorMessage = result.fieldErrors?.get("email") ?: result.message
                    setFieldError(requireContext(), binding.tilEmail, errorMessage)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
