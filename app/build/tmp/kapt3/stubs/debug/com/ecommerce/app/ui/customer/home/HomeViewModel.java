package com.ecommerce.app.ui.customer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.PageResponse;
import com.ecommerce.app.data.model.ProductResponse;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.NetworkResult;
import com.ecommerce.app.util.TokenManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J.\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/ecommerce/app/ui/customer/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/ecommerce/app/data/repository/ProductRepository;", "(Lcom/ecommerce/app/data/repository/ProductRepository;)V", "_firstName", "Landroidx/lifecycle/MutableLiveData;", "", "_productsState", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/PageResponse;", "Lcom/ecommerce/app/data/model/ProductResponse;", "currentPage", "", "firstName", "Landroidx/lifecycle/LiveData;", "getFirstName", "()Landroidx/lifecycle/LiveData;", "isLastPage", "", "productsState", "getProductsState", "loadNextPage", "", "loadProducts", "page", "name", "categoryIds", "", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.ProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.ProductResponse>>> _productsState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.ProductResponse>>> productsState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _firstName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> firstName = null;
    private int currentPage = 0;
    private boolean isLastPage = false;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.ProductRepository productRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.ProductResponse>>> getProductsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFirstName() {
        return null;
    }
    
    public final void loadProducts(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> categoryIds) {
    }
    
    public final void loadNextPage() {
    }
}