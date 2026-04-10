package com.ecommerce.app.ui.customer.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import com.ecommerce.app.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ecommerce.app.databinding.FragmentPaymentSuccessBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentSuccessFragment : Fragment() {

    private var _binding: FragmentPaymentSuccessBinding? = null
    private val binding get() = _binding!!
    private val args: PaymentSuccessFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)
            if (_binding == null) return@launch
            findNavController().navigate(
                R.id.action_paymentSuccessFragment_to_ordersFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, false)
                    .build()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}