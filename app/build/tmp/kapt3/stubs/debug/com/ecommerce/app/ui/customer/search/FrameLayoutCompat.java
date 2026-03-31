package com.ecommerce.app.ui.customer.search;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.ecommerce.app.R;
import com.ecommerce.app.data.model.category.CategoryResponse;
import com.ecommerce.app.databinding.FragmentSearchBinding;
import com.ecommerce.app.ui.customer.products.ProductAdapter;
import com.ecommerce.app.util.NetworkResult;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/ecommerce/app/ui/customer/search/FrameLayoutCompat;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "app_debug"})
final class FrameLayoutCompat extends android.widget.FrameLayout {
    
    public FrameLayoutCompat(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
}