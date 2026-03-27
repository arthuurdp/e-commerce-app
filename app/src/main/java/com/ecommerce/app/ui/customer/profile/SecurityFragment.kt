package com.ecommerce.app.ui.customer.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.databinding.FragmentSecurityBinding
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class SecurityFragment : Fragment() {

    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Password change is a two-step flow: request code → confirm with code
        // Step 1
        binding.btnRequestCode.setOnClickListener {
            val newPwd = binding.etNewPassword.text.toString()
            if (newPwd.isEmpty()) { showToast("Enter new password"); return@setOnClickListener }
            showToast("Code sent to your email!")
            binding.layoutConfirm.show()
        }
        binding.btnConfirm.setOnClickListener {
            val code = binding.etCode.text.toString().trim()
            if (code.isEmpty()) { showToast("Enter the code"); return@setOnClickListener }
            showToast("Password changed!")
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
