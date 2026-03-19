package com.ecommerce.app.ui.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.databinding.FragmentForgotPasswordEnterCodeBinding
import com.ecommerce.app.ui.auth.AuthViewModel
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
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

        binding.tilEmail.show()
        binding.btnSendCode.show()
        binding.btnSendCode.text = "Confirm Code"

        binding.btnSendCode.setOnClickListener {
            val code = binding.etEnterCode.text.toString().trim()
            if (code.length == 6) {
                val action = ForgotPasswordEnterCodeFragmentDirections.actionEnterCodeFragmentToResetPasswordFragment(args.email, code)
                findNavController().navigate(action)
            } else {
                showToast("Please enter a valid 6-digit code")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
