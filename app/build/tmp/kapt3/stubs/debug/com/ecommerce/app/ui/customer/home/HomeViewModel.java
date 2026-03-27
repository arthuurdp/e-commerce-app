package com.ecommerce.app.ui.customer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.category.CategoryResponse;
import com.ecommerce.app.data.model.product.ProductResponse;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.data.repository.CategoryRepository;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0014\u0010\u001f\u001a\u00020\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u0012R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00110\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\u001a\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00110\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/ecommerce/app/data/repository/ProductRepository;", "categoryRepository", "Lcom/ecommerce/app/data/repository/CategoryRepository;", "userRepository", "Lcom/ecommerce/app/data/repository/UserRepository;", "(Lcom/ecommerce/app/data/repository/ProductRepository;Lcom/ecommerce/app/data/repository/CategoryRepository;Lcom/ecommerce/app/data/repository/UserRepository;)V", "_categoriesState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/category/CategoryResponse;", "_firstName", "", "_productsByCategory", "", "", "Lcom/ecommerce/app/data/model/product/ProductResponse;", "categoriesState", "Landroidx/lifecycle/LiveData;", "getCategoriesState", "()Landroidx/lifecycle/LiveData;", "firstName", "getFirstName", "productsByCategory", "getProductsByCategory", "loadCategories", "", "loadFirstName", "loadProductsByCategories", "categories", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.ProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.category.CategoryResponse>>> _categoriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.category.CategoryResponse>>> categoriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<com.ecommerce.app.data.model.category.CategoryResponse, java.util.List<com.ecommerce.app.data.model.product.ProductResponse>>> _productsByCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<com.ecommerce.app.data.model.category.CategoryResponse, java.util.List<com.ecommerce.app.data.model.product.ProductResponse>>> productsByCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _firstName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> firstName = null;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.ProductRepository productRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.CategoryRepository categoryRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.category.CategoryResponse>>> getCategoriesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<com.ecommerce.app.data.model.category.CategoryResponse, java.util.List<com.ecommerce.app.data.model.product.ProductResponse>>> getProductsByCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getFirstName() {
        return null;
    }
    
    public final void loadCategories() {
    }
    
    public final void loadFirstName() {
    }
    
    public final void loadProductsByCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ecommerce.app.data.model.category.CategoryResponse> categories) {
    }
}