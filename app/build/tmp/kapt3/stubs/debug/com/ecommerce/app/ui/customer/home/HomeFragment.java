package com.ecommerce.app.ui.customer.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.ecommerce.app.R;
import com.ecommerce.app.data.model.category.CategoryResponse;
import com.ecommerce.app.data.model.product.ProductResponse;
import com.ecommerce.app.databinding.FragmentHomeBinding;
import com.ecommerce.app.ui.customer.products.ProductAdapter;
import com.ecommerce.app.util.NetworkResult;
import com.google.android.material.card.MaterialCardView;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\"\u0010\u0014\u001a\u00020\u00152\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0017H\u0002J\u0016\u0010\u001b\u001a\u00020\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u0019H\u0002J.\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u000f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150#H\u0002J\u001a\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00132\b\u0010\'\u001a\u0004\u0018\u00010\u0013H\u0002J\u0018\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u000fH\u0002J\b\u0010+\u001a\u00020\u0015H\u0002J\b\u0010,\u001a\u00020\u0015H\u0002J\b\u0010-\u001a\u00020\u0015H\u0002J\b\u0010.\u001a\u00020\u0015H\u0002J$\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020\u0015H\u0016J\u001a\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u0002002\b\u00105\u001a\u0004\u0018\u000106H\u0016J\u0010\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u001fH\u0002J\b\u0010<\u001a\u00020\u0015H\u0002J\u0010\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u000fH\u0002J\b\u0010?\u001a\u00020\u0015H\u0002J\u0010\u0010@\u001a\u00020\u00152\u0006\u0010A\u001a\u00020BH\u0002J\u0018\u0010C\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u000f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006E"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentHomeBinding;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentHomeBinding;", "viewModel", "Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "dp", "", "getDp", "(I)I", "buildAllTile", "Lcom/google/android/material/card/MaterialCardView;", "buildCategorySections", "", "grouped", "", "Lcom/ecommerce/app/data/model/category/CategoryResponse;", "", "Lcom/ecommerce/app/data/model/product/ProductResponse;", "buildCategoryTiles", "categories", "buildColoredTile", "label", "", "emoji", "colorInt", "onClick", "Lkotlin/Function0;", "buildRow", "Landroid/widget/LinearLayout;", "left", "right", "buildTile", "category", "colorIndex", "loadFirstName", "observeCart", "observeCategories", "observeProductsByCategory", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "resolveEmoji", "name", "setupBanner", "setupDots", "count", "setupSwipeRefresh", "startAutoScroll", "adapter", "Lcom/ecommerce/app/ui/customer/home/BannerAdapter;", "updateDots", "selected", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.ecommerce.app.databinding.FragmentHomeBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public HomeFragment() {
        super();
    }
    
    private final com.ecommerce.app.databinding.FragmentHomeBinding getBinding() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.home.HomeViewModel getViewModel() {
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
    
    private final void setupBanner() {
    }
    
    private final void setupDots(int count) {
    }
    
    private final void updateDots(int selected, int count) {
    }
    
    private final void observeCategories() {
    }
    
    private final void buildCategoryTiles(java.util.List<com.ecommerce.app.data.model.category.CategoryResponse> categories) {
    }
    
    private final com.google.android.material.card.MaterialCardView buildAllTile() {
        return null;
    }
    
    private final com.google.android.material.card.MaterialCardView buildTile(com.ecommerce.app.data.model.category.CategoryResponse category, int colorIndex) {
        return null;
    }
    
    private final com.google.android.material.card.MaterialCardView buildColoredTile(java.lang.String label, java.lang.String emoji, int colorInt, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
        return null;
    }
    
    private final android.widget.LinearLayout buildRow(com.google.android.material.card.MaterialCardView left, com.google.android.material.card.MaterialCardView right) {
        return null;
    }
    
    private final void observeProductsByCategory() {
    }
    
    private final void buildCategorySections(java.util.Map<com.ecommerce.app.data.model.category.CategoryResponse, ? extends java.util.List<com.ecommerce.app.data.model.product.ProductResponse>> grouped) {
    }
    
    private final void startAutoScroll(com.ecommerce.app.ui.customer.home.BannerAdapter adapter) {
    }
    
    private final void loadFirstName() {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final void observeCart() {
    }
    
    private final java.lang.String resolveEmoji(java.lang.String name) {
        return null;
    }
    
    private final int getDp(int $this$dp) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}