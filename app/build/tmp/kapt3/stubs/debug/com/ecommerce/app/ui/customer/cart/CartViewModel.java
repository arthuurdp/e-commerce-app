package com.ecommerce.app.ui.customer.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.cart.CartResponse;
import com.ecommerce.app.data.repository.CartRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0014"}, d2 = {"Lcom/ecommerce/app/ui/customer/cart/CartViewModel;", "Landroidx/lifecycle/ViewModel;", "cartRepository", "Lcom/ecommerce/app/data/repository/CartRepository;", "(Lcom/ecommerce/app/data/repository/CartRepository;)V", "_cartState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/cart/CartResponse;", "cartState", "Landroidx/lifecycle/LiveData;", "getCartState", "()Landroidx/lifecycle/LiveData;", "addToCart", "", "productId", "", "clearCart", "loadCart", "removeFromCart", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CartViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.CartRepository cartRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartResponse>> _cartState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartResponse>> cartState = null;
    
    @javax.inject.Inject()
    public CartViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.CartRepository cartRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartResponse>> getCartState() {
        return null;
    }
    
    public final void loadCart() {
    }
    
    public final void addToCart(long productId) {
    }
    
    public final void removeFromCart(long productId) {
    }
    
    public final void clearCart() {
    }
}