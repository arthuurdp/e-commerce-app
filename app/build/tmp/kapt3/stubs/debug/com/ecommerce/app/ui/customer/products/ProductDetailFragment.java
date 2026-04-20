package com.ecommerce.app.ui.customer.products;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.ecommerce.app.R;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.ecommerce.app.data.model.product.ProductDetailsResponse;
import com.ecommerce.app.data.model.product.ProductImageResponse;
import com.ecommerce.app.databinding.FragmentProductDetailBinding;
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel;
import com.ecommerce.app.util.*;
import com.google.android.material.button.MaterialButton;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010\'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020#H\u0002J\b\u0010)\u001a\u00020#H\u0002J\b\u0010*\u001a\u00020#H\u0002J\b\u0010+\u001a\u00020#H\u0002J\b\u0010,\u001a\u00020#H\u0002J$\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u000104H\u0016J\b\u00105\u001a\u00020#H\u0016J\u001a\u00106\u001a\u00020#2\u0006\u00107\u001a\u0002082\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0010\u00109\u001a\u00020#2\u0006\u0010:\u001a\u00020\u001fH\u0002J\u0016\u0010;\u001a\u00020#2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=H\u0002J\b\u0010?\u001a\u00020#H\u0002J\u0010\u0010@\u001a\u00020#2\u0006\u0010A\u001a\u00020\u000bH\u0002J\b\u0010B\u001a\u00020#H\u0002J\b\u0010C\u001a\u00020#H\u0002J\b\u0010D\u001a\u00020#H\u0002J\u0018\u0010E\u001a\u00020#2\u0006\u0010F\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020\u001fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u00020\u001f*\u00020\u001f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010!\u00a8\u0006G"}, d2 = {"Lcom/ecommerce/app/ui/customer/products/ProductDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentProductDetailBinding;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentProductDetailBinding;", "commentsExpanded", "", "currentProductId", "", "currentUserId", "Ljava/lang/Long;", "hasReviewsWithComments", "reviewsAdapter", "Lcom/ecommerce/app/ui/customer/products/ProductReviewsAdapter;", "securityViewModel", "Lcom/ecommerce/app/ui/customer/profile/security/SecurityViewModel;", "getSecurityViewModel", "()Lcom/ecommerce/app/ui/customer/profile/security/SecurityViewModel;", "securityViewModel$delegate", "Lkotlin/Lazy;", "userEmail", "", "viewModel", "Lcom/ecommerce/app/ui/customer/products/ProductDetailViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/customer/products/ProductDetailViewModel;", "viewModel$delegate", "dp", "", "getDp", "(I)I", "bindProduct", "", "p", "Lcom/ecommerce/app/data/model/product/ProductDetailsResponse;", "bindStock", "stock", "observeAddReview", "observeAddToCart", "observeFavorite", "observeProduct", "observeReviews", "onCreateView", "Landroid/widget/FrameLayout;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "Landroid/view/View;", "setupDots", "count", "setupImageCarousel", "imgs", "", "Lcom/ecommerce/app/data/model/product/ProductImageResponse;", "setupReviewsRecyclerView", "showAddReviewDialog", "productId", "showProductAddedToCartDialog", "showVerifyEmailDialog", "toggleReviews", "updateDots", "selected", "app_debug"})
public final class ProductDetailFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.ecommerce.app.databinding.FragmentProductDetailBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy securityViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String userEmail;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long currentUserId;
    private long currentProductId = 0L;
    private boolean commentsExpanded = true;
    private boolean hasReviewsWithComments = false;
    private com.ecommerce.app.ui.customer.products.ProductReviewsAdapter reviewsAdapter;
    
    public ProductDetailFragment() {
        super();
    }
    
    private final com.ecommerce.app.databinding.FragmentProductDetailBinding getBinding() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.products.ProductDetailViewModel getViewModel() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.profile.security.SecurityViewModel getSecurityViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.widget.FrameLayout onCreateView(@org.jetbrains.annotations.NotNull()
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
    
    private final void setupReviewsRecyclerView() {
    }
    
    private final void toggleReviews() {
    }
    
    private final void observeReviews() {
    }
    
    private final void observeProduct() {
    }
    
    private final void bindProduct(com.ecommerce.app.data.model.product.ProductDetailsResponse p) {
    }
    
    private final void bindStock(int stock) {
    }
    
    private final void observeAddToCart() {
    }
    
    private final void observeAddReview() {
    }
    
    private final void showAddReviewDialog(long productId) {
    }
    
    private final void showVerifyEmailDialog() {
    }
    
    private final void observeFavorite() {
    }
    
    private final void showProductAddedToCartDialog() {
    }
    
    private final void setupImageCarousel(java.util.List<com.ecommerce.app.data.model.product.ProductImageResponse> imgs) {
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