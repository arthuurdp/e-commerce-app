package com.ecommerce.app.ui.customer.profile.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.databinding.FragmentChangeEmailEnterCodeBinding
import com.ecommerce.app.util.setFieldError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeEmailEnterCodeFragment: Fragment() {
    private val viewModel: SecurityViewModel by viewModels()
    private var _binding: FragmentChangeEmailEnterCodeBinding? = null

    private val binding get() = _binding!!

    private val args: ChangeEmailEnterCodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeEmailEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSendCode.setOnClickListener { attemptVerifyCode() }
    }

    private fun attemptVerifyCode() {
        val code = binding.etEnterCode.text.toString().trim()

        if (code.length == 6) {
            viewModel.confirmEmailChange(code)
        } else {
            setFieldError(requireContext(), binding.tilEnterCode, "Enter the 6-digit code")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}