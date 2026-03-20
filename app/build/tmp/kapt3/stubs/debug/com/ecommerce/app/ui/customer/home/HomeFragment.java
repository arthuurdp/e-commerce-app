package com.ecommerce.app.ui.customer.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.ecommerce.app.R;
import com.ecommerce.app.databinding.FragmentHomeBinding;
import com.ecommerce.app.data.model.CategoryResponse;
import com.ecommerce.app.data.model.ProductResponse;
import com.ecommerce.app.ui.customer.products.ProductAdapter;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.AndroidEntryPoint;
import com.google.android.material.chip.Chip;
import androidx.appcompat.widget.SearchView;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0015H\u0002J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u0013H\u0016J\u001a\u0010\'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010)\u001a\u00020\u0013H\u0002J\u0016\u0010*\u001a\u00020\u00132\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00160\u0017H\u0002J\u0010\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u0013H\u0002J\u0018\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u000f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u00061"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentHomeBinding;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentHomeBinding;", "viewModel", "Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "dp", "", "getDp", "(I)I", "buildCategorySections", "", "grouped", "", "Lcom/ecommerce/app/data/model/CategoryResponse;", "", "Lcom/ecommerce/app/data/model/ProductResponse;", "filterChips", "query", "", "observeCategories", "observeProductsByCategory", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupBanner", "setupCategoryChips", "categories", "setupDots", "count", "setupSwipeRefresh", "updateDots", "selected", "app_debug"})
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
    
    private final void observeProductsByCategory() {
    }
    
    private final void filterChips(java.lang.String query) {
    }
    
    private final void buildCategorySections(java.util.Map<com.ecommerce.app.data.model.CategoryResponse, ? extends java.util.List<com.ecommerce.app.data.model.ProductResponse>> grouped) {
    }
    
    private final void setupCategoryChips(java.util.List<com.ecommerce.app.data.model.CategoryResponse> categories) {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final int getDp(int $this$dp) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}