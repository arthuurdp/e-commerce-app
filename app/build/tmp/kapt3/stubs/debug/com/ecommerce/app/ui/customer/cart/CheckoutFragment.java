package com.ecommerce.app.ui.customer.cart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.ecommerce.app.data.model.address.AddressResponse;
import com.ecommerce.app.data.model.order.CheckoutRequest;
import com.ecommerce.app.data.model.shipping.FreightResponse;
import com.ecommerce.app.databinding.FragmentCheckoutBinding;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.AndroidEntryPoint;
import java.text.NumberFormat;
import java.util.Locale;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0002J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\u001a\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010+\u001a\u00020\u0019H\u0002J\b\u0010,\u001a\u00020\u0019H\u0002J\b\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020\u0019H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006/"}, d2 = {"Lcom/ecommerce/app/ui/customer/cart/CheckoutFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentCheckoutBinding;", "addresses", "", "Lcom/ecommerce/app/data/model/address/AddressResponse;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentCheckoutBinding;", "cartSubtotal", "", "currencyFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "freightOptions", "Lcom/ecommerce/app/data/model/shipping/FreightResponse;", "viewModel", "Lcom/ecommerce/app/ui/customer/cart/CheckoutViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/customer/cart/CheckoutViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "buildOrderItemsView", "", "cart", "Lcom/ecommerce/app/data/model/cart/CartResponse;", "observeAddresses", "observeCart", "observeCheckout", "observeFreight", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "placeOrder", "setupAddressSpinnerListener", "setupPaymentMethods", "updateTotals", "app_debug"})
public final class CheckoutFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.ecommerce.app.databinding.FragmentCheckoutBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.ecommerce.app.data.model.address.AddressResponse> addresses;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.ecommerce.app.data.model.shipping.FreightResponse> freightOptions;
    private double cartSubtotal = 0.0;
    private final java.text.NumberFormat currencyFormat = null;
    
    public CheckoutFragment() {
        super();
    }
    
    private final com.ecommerce.app.databinding.FragmentCheckoutBinding getBinding() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.cart.CheckoutViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupPaymentMethods() {
    }
    
    private final void setupAddressSpinnerListener() {
    }
    
    private final void observeCart() {
    }
    
    private final void observeAddresses() {
    }
    
    private final void observeFreight() {
    }
    
    private final void observeCheckout() {
    }
    
    private final void buildOrderItemsView(com.ecommerce.app.data.model.cart.CartResponse cart) {
    }
    
    private final void updateTotals() {
    }
    
    private final void placeOrder() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}