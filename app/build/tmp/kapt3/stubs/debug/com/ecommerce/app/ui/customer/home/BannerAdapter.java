package com.ecommerce.app.ui.customer.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.app.databinding.ItemBannerBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0011B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\f\u001a\u00020\bH\u0016J\u001c\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/BannerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ecommerce/app/ui/customer/home/BannerAdapter$BannerViewHolder;", "items", "", "Lcom/ecommerce/app/ui/customer/home/BannerItem;", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BannerViewHolder", "app_debug"})
public final class BannerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ecommerce.app.ui.customer.home.BannerAdapter.BannerViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.ecommerce.app.ui.customer.home.BannerItem> items = null;
    
    public BannerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ecommerce.app.ui.customer.home.BannerItem> items) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.ecommerce.app.ui.customer.home.BannerAdapter.BannerViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.ui.customer.home.BannerAdapter.BannerViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/BannerAdapter$BannerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ecommerce/app/databinding/ItemBannerBinding;", "(Lcom/ecommerce/app/ui/customer/home/BannerAdapter;Lcom/ecommerce/app/databinding/ItemBannerBinding;)V", "bind", "", "item", "Lcom/ecommerce/app/ui/customer/home/BannerItem;", "app_debug"})
    public final class BannerViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.ecommerce.app.databinding.ItemBannerBinding binding = null;
        
        public BannerViewHolder(@org.jetbrains.annotations.NotNull()
        com.ecommerce.app.databinding.ItemBannerBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.ecommerce.app.ui.customer.home.BannerItem item) {
        }
    }
}