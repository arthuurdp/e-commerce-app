package com.ecommerce.app.ui.customer.profile.security

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentSecurityBinding
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecurityFragment : Fragment() {

    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SecurityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.headerChangeEmail.setOnClickListener {
            findNavController().navigate(R.id.action_securityFragment_to_changeEmailFragment)
        }

        binding.headerChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_securityFragment_to_changePasswordFragment)
        }

        binding.headerDeleteAccount.setOnClickListener {
            showDeleteAccountDialog()
        }
    }

    private fun observeViewModel() {
        viewModel.deleteState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> setDeleteAccountLoading(true)
                is NetworkResult.Success -> {
                    setDeleteAccountLoading(false)
                    findNavController().navigate(R.id.action_securityFragment_to_loginFragment)
                }
                is NetworkResult.Error -> {
                    setDeleteAccountLoading(false)
                }
            }
        }
    }

    private fun showDeleteAccountDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_delete_account)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.findViewById<View>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<View>(R.id.btnConfirm).setOnClickListener {
            dialog.dismiss()
            viewModel.deleteAccount()
        }

        dialog.show()
    }

    private fun setDeleteAccountLoading(loading: Boolean) {
        binding.headerDeleteAccount.isEnabled = !loading
        binding.cardDeleteAccount.alpha = if (loading) 0.6f else 1f
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}