package com.ecommerce.app.ui.customer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.CategoryResponse;
import com.ecommerce.app.data.model.PageResponse;
import com.ecommerce.app.data.model.ProductResponse;
import com.ecommerce.app.data.repository.CategoryRepository;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0016\u001a\u00020\u0017J\u0014\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eR \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\u0014\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013\u00a8\u0006\u001a"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/ecommerce/app/data/repository/ProductRepository;", "categoryRepository", "Lcom/ecommerce/app/data/repository/CategoryRepository;", "(Lcom/ecommerce/app/data/repository/ProductRepository;Lcom/ecommerce/app/data/repository/CategoryRepository;)V", "_categoriesState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/PageResponse;", "Lcom/ecommerce/app/data/model/CategoryResponse;", "_productsByCategory", "", "", "Lcom/ecommerce/app/data/model/ProductResponse;", "categoriesState", "Landroidx/lifecycle/LiveData;", "getCategoriesState", "()Landroidx/lifecycle/LiveData;", "productsByCategory", "getProductsByCategory", "loadCategories", "", "loadProductsByCategories", "categories", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.ProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.CategoryResponse>>> _categoriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.CategoryResponse>>> categoriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<com.ecommerce.app.data.model.CategoryResponse, java.util.List<com.ecommerce.app.data.model.ProductResponse>>> _productsByCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<com.ecommerce.app.data.model.CategoryResponse, java.util.List<com.ecommerce.app.data.model.ProductResponse>>> productsByCategory = null;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.ProductRepository productRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.CategoryResponse>>> getCategoriesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<com.ecommerce.app.data.model.CategoryResponse, java.util.List<com.ecommerce.app.data.model.ProductResponse>>> getProductsByCategory() {
        return null;
    }
    
    public final void loadCategories() {
    }
    
    public final void loadProductsByCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ecommerce.app.data.model.CategoryResponse> categories) {
    }
}