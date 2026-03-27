package com.ecommerce.app.ui.customer.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.order.OrderDetailsResponse;
import com.ecommerce.app.data.repository.OrderRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/ecommerce/app/ui/customer/orders/OrderDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "orderRepository", "Lcom/ecommerce/app/data/repository/OrderRepository;", "(Lcom/ecommerce/app/data/repository/OrderRepository;)V", "_orderState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/order/OrderDetailsResponse;", "orderState", "Landroidx/lifecycle/LiveData;", "getOrderState", "()Landroidx/lifecycle/LiveData;", "loadOrder", "", "id", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class OrderDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.OrderRepository orderRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.order.OrderDetailsResponse>> _orderState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.order.OrderDetailsResponse>> orderState = null;
    
    @javax.inject.Inject()
    public OrderDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.OrderRepository orderRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.order.OrderDetailsResponse>> getOrderState() {
        return null;
    }
    
    public final void loadOrder(long id) {
    }
}