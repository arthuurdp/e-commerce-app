package com.ecommerce.app.ui.customer.search;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.ecommerce.app.R;
import com.ecommerce.app.data.model.category.CategoryResponse;
import com.ecommerce.app.databinding.FragmentSearchBinding;
import com.ecommerce.app.ui.customer.products.ProductSearchAdapter;
import com.ecommerce.app.util.NetworkResult;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J(\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001bH\u0002J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u0014H\u0002J\b\u0010\'\u001a\u00020\u0014H\u0002J\b\u0010(\u001a\u00020\u0014H\u0002J\b\u0010)\u001a\u00020\u0014H\u0002J$\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00102\u001a\u00020\u0014H\u0016J\u001a\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0010\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u001fH\u0002J\u0018\u00107\u001a\u00020\u00142\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u001dH\u0002J\b\u0010;\u001a\u00020\u0014H\u0002J\b\u0010<\u001a\u00020\u0014H\u0002J\b\u0010=\u001a\u00020\u0014H\u0002J\b\u0010>\u001a\u00020\u0014H\u0002J\b\u0010?\u001a\u00020\u0014H\u0002J\b\u0010@\u001a\u00020\u0014H\u0002J\b\u0010A\u001a\u00020\u0014H\u0002J\f\u0010$\u001a\u00020B*\u00020BH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006C"}, d2 = {"Lcom/ecommerce/app/ui/customer/search/SearchFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentSearchBinding;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentSearchBinding;", "loadedCategories", "", "Lcom/ecommerce/app/data/model/category/CategoryResponse;", "resultsAdapter", "Lcom/ecommerce/app/ui/customer/products/ProductSearchAdapter;", "viewModel", "Lcom/ecommerce/app/ui/customer/search/SearchViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/customer/search/SearchViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "buildCategoryChips", "", "categories", "buildCategoryGrid", "buildCategoryTile", "Lcom/google/android/material/card/MaterialCardView;", "category", "colorInt", "", "createChip", "Lcom/google/android/material/chip/Chip;", "label", "", "emoji", "isSelected", "", "accentColor", "dpToPx", "dp", "hideAllStates", "hideChipRow", "observeCategories", "observeSearch", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "resolveEmoji", "name", "selectChip", "group", "Lcom/google/android/material/chip/ChipGroup;", "selected", "setupResultsList", "setupSearchBar", "showChipRow", "showEmptyState", "showNoResults", "showResults", "syncSelectedChip", "", "app_debug"})
public final class SearchFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.ecommerce.app.databinding.FragmentSearchBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ecommerce.app.ui.customer.products.ProductSearchAdapter resultsAdapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.ecommerce.app.data.model.category.CategoryResponse> loadedCategories;
    
    public SearchFragment() {
        super();
    }
    
    private final com.ecommerce.app.databinding.FragmentSearchBinding getBinding() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.search.SearchViewModel getViewModel() {
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
    
    private final void setupResultsList() {
    }
    
    private final void setupSearchBar() {
    }
    
    private final void showChipRow() {
    }
    
    private final void hideChipRow() {
    }
    
    private final void observeCategories() {
    }
    
    private final void observeSearch() {
    }
    
    private final void buildCategoryChips(java.util.List<com.ecommerce.app.data.model.category.CategoryResponse> categories) {
    }
    
    private final com.google.android.material.chip.Chip createChip(java.lang.String label, java.lang.String emoji, boolean isSelected, int accentColor) {
        return null;
    }
    
    private final void selectChip(com.google.android.material.chip.ChipGroup group, com.google.android.material.chip.Chip selected) {
    }
    
    private final void syncSelectedChip() {
    }
    
    private final void buildCategoryGrid(java.util.List<com.ecommerce.app.data.model.category.CategoryResponse> categories) {
    }
    
    private final com.google.android.material.card.MaterialCardView buildCategoryTile(com.ecommerce.app.data.model.category.CategoryResponse category, int colorInt) {
        return null;
    }
    
    private final void hideAllStates() {
    }
    
    private final void showEmptyState() {
    }
    
    private final void showResults() {
    }
    
    private final void showNoResults() {
    }
    
    private final java.lang.String resolveEmoji(java.lang.String name) {
        return null;
    }
    
    private final int dpToPx(int dp) {
        return 0;
    }
    
    private final float dpToPx(float $this$dpToPx) {
        return 0.0F;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}