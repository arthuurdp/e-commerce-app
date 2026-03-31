package com.ecommerce.app.ui.customer.profile.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.databinding.FragmentChangeEmailEnterNewEmailBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hideKeyboard
import com.ecommerce.app.util.setFieldError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeEmailEnterNewEmailFragment : Fragment() {

    private val viewModel: SecurityViewModel by viewModels()
    private var _binding: FragmentChangeEmailEnterNewEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeEmailEnterNewEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainContainer.setOnClickListener { hideKeyboard() }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSendCode.setOnClickListener { attemptSendCode() }

        observeState()
    }

    private fun attemptSendCode() {
        val email = binding.etNewEmail.text.toString().trim()
        setFieldError(requireContext(), binding.tilNewEmail, null)

        if (email.isEmpty()) {
            setFieldError(requireContext(), binding.tilNewEmail, "Email cannot be empty")
            return
        }

        viewModel.requestEmailChange(email)
    }

    private fun observeState() {
        viewModel.requestEmailChangeState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.btnSendCode.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.btnSendCode.isEnabled = true
                    val action = ChangeEmailEnterNewEmailFragmentDirections
                        .actionChangeEmailFragmentToEnterCodeFragment(
                            mode = "CHANGE_EMAIL",
                            email = binding.etNewEmail.text.toString().trim()
                        )
                    findNavController().navigate(action)
                }
                is NetworkResult.Error -> {
                    binding.btnSendCode.isEnabled = true
                    val msg = result.fieldErrors?.get("email") ?: result.message
                    setFieldError(requireContext(), binding.tilNewEmail, msg)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
