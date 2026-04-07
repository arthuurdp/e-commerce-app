package com.ecommerce.app.ui.customer.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class PaymentStatus { WAITING, SUCCESS, FAILURE }

@HiltViewModel
class PaymentWaitingViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _paymentStatus = MutableLiveData(PaymentStatus.WAITING)
    val paymentStatus: LiveData<PaymentStatus> = _paymentStatus

    private var pollingJob: Job? = null

    fun startPolling(orderId: Long) {
        if (pollingJob?.isActive == true) return
        pollingJob = viewModelScope.launch {
            repeat(40) {
                val result = orderRepository.getOrderById(orderId)
                if (result is NetworkResult.Success) {
                    val status = result.data.status
                    when {
                        status == "PAID" || status == "PROCESSING" || status == "SHIPPED" -> {
                            _paymentStatus.value = PaymentStatus.SUCCESS
                            return@launch
                        }
                        status == "CANCELED" || status == "PAYMENT_FAILED" -> {
                            _paymentStatus.value = PaymentStatus.FAILURE
                            return@launch
                        }
                    }
                }
                delay(3_000)
            }
            _paymentStatus.value = PaymentStatus.FAILURE
        }
    }

    fun confirmSuccess() {
        pollingJob?.cancel()
        _paymentStatus.value = PaymentStatus.SUCCESS
    }

    fun confirmFailure() {
        pollingJob?.cancel()
        _paymentStatus.value = PaymentStatus.FAILURE
    }

    override fun onCleared() {
        super.onCleared()
        pollingJob?.cancel()
    }
}