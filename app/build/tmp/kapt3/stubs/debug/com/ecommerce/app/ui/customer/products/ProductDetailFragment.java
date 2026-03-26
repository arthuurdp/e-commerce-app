package com.ecommerce.app.ui.customer.products;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.ecommerce.app.R;
import androidx.viewpager2.widget.ViewPager2;
import com.ecommerce.app.data.model.ProductDetailsResponse;
import com.ecommerce.app.data.model.ProductImageResponse;
import com.ecommerce.app.databinding.FragmentProductDetailBinding;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\u001a\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u000fH\u0002J\u0016\u0010&\u001a\u00020\u00132\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0002J\b\u0010*\u001a\u00020\u0013H\u0002J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u000f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/ecommerce/app/ui/customer/products/ProductDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentProductDetailBinding;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentProductDetailBinding;", "viewModel", "Lcom/ecommerce/app/ui/customer/products/ProductDetailViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/customer/products/ProductDetailViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "dp", "", "getDp", "(I)I", "bindProduct", "", "p", "Lcom/ecommerce/app/data/model/ProductDetailsResponse;", "observeAddToCart", "observeProduct", "onCreateView", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "Landroid/view/View;", "setupDots", "count", "setupImageCarousel", "imgs", "", "Lcom/ecommerce/app/data/model/ProductImageResponse;", "showVerifyEmailDialog", "updateDots", "selected", "app_debug"})
public final class ProductDetailFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.ecommerce.app.databinding.FragmentProductDetailBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public ProductDetailFragment() {
        super();
    }
    
    private final com.ecommerce.app.databinding.FragmentProductDetailBinding getBinding() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.products.ProductDetailViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.coordinatorlayout.widget.CoordinatorLayout onCreateView(@org.jetbrains.annotations.NotNull()
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
    
    private final void observeProduct() {
    }
    
    private final void bindProduct(com.ecommerce.app.data.model.ProductDetailsResponse p) {
    }
    
    private final void observeAddToCart() {
    }
    
    private final void showVerifyEmailDialog() {
    }
    
    private final void setupImageCarousel(java.util.List<com.ecommerce.app.data.model.ProductImageResponse> imgs) {
    }
    
    private final void setupDots(int count) {
    }
    
    private final void updateDots(int selected, int count) {
    }
    
    private final int getDp(int $this$dp) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}