package com.ecommerce.app.ui.admin.categories;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.app.data.model.category.CategoryResponse;
import com.ecommerce.app.databinding.ItemCategoryBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/ecommerce/app/ui/admin/categories/CategoryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/ecommerce/app/data/model/category/CategoryResponse;", "Lcom/ecommerce/app/ui/admin/categories/CategoryAdapter$VH;", "onDelete", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Diff", "VH", "app_debug"})
public final class CategoryAdapter extends androidx.recyclerview.widget.ListAdapter<com.ecommerce.app.data.model.category.CategoryResponse, com.ecommerce.app.ui.admin.categories.CategoryAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.ecommerce.app.data.model.category.CategoryResponse, kotlin.Unit> onDelete = null;
    
    public CategoryAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.ecommerce.app.data.model.category.CategoryResponse, kotlin.Unit> onDelete) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.ecommerce.app.ui.admin.categories.CategoryAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.ui.admin.categories.CategoryAdapter.VH holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/ecommerce/app/ui/admin/categories/CategoryAdapter$Diff;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/ecommerce/app/data/model/category/CategoryResponse;", "()V", "areContentsTheSame", "", "a", "b", "areItemsTheSame", "app_debug"})
    public static final class Diff extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.ecommerce.app.data.model.category.CategoryResponse> {
        @org.jetbrains.annotations.NotNull()
        public static final com.ecommerce.app.ui.admin.categories.CategoryAdapter.Diff INSTANCE = null;
        
        private Diff() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.ecommerce.app.data.model.category.CategoryResponse a, @org.jetbrains.annotations.NotNull()
        com.ecommerce.app.data.model.category.CategoryResponse b) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.ecommerce.app.data.model.category.CategoryResponse a, @org.jetbrains.annotations.NotNull()
        com.ecommerce.app.data.model.category.CategoryResponse b) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/ecommerce/app/ui/admin/categories/CategoryAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ecommerce/app/databinding/ItemCategoryBinding;", "(Lcom/ecommerce/app/ui/admin/categories/CategoryAdapter;Lcom/ecommerce/app/databinding/ItemCategoryBinding;)V", "getBinding", "()Lcom/ecommerce/app/databinding/ItemCategoryBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.ecommerce.app.databinding.ItemCategoryBinding binding = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.ecommerce.app.databinding.ItemCategoryBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ecommerce.app.databinding.ItemCategoryBinding getBinding() {
            return null;
        }
    }
}