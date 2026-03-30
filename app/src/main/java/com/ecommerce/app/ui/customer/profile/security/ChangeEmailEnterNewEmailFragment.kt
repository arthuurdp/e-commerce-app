package com.ecommerce.app.ui.customer.profile.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecommerce.app.databinding.FragmentChangeEmailEnterNewEmailBinding
import com.ecommerce.app.util.setFieldError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeEmailEnterNewEmailFragment : Fragment() {
    private val viewModel: SecurityViewModel by viewModels()
    private var _binding: FragmentChangeEmailEnterNewEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChangeEmailEnterNewEmailBinding.bind(view)

        binding.btnSendCode.setOnClickListener {
            attemptSendCode()
        }
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}