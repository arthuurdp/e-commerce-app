package com.ecommerce.app.ui.admin.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.product.CreateProductRequest;
import com.ecommerce.app.data.model.product.ProductDetailsResponse;
import com.ecommerce.app.data.model.product.ProductResponse;
import com.ecommerce.app.data.model.product.UpdateProductRequest;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\bJ\u0016\u0010!\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\"R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012\u00a8\u0006#"}, d2 = {"Lcom/ecommerce/app/ui/admin/products/AdminProductsViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/ecommerce/app/data/repository/ProductRepository;", "(Lcom/ecommerce/app/data/repository/ProductRepository;)V", "_deleteState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "", "_productDetailState", "Lcom/ecommerce/app/data/model/product/ProductDetailsResponse;", "_productsState", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/product/ProductResponse;", "_saveState", "deleteState", "Landroidx/lifecycle/LiveData;", "getDeleteState", "()Landroidx/lifecycle/LiveData;", "productDetailState", "getProductDetailState", "productsState", "getProductsState", "saveState", "getSaveState", "createProduct", "request", "Lcom/ecommerce/app/data/model/product/CreateProductRequest;", "deleteProduct", "id", "", "loadProductDetail", "loadProducts", "updateProduct", "Lcom/ecommerce/app/data/model/product/UpdateProductRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AdminProductsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.ProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.product.ProductResponse>>> _productsState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.product.ProductResponse>>> productsState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> _productDetailState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> productDetailState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> _saveState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> saveState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> _deleteState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> deleteState = null;
    
    @javax.inject.Inject()
    public AdminProductsViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.ProductRepository productRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.product.ProductResponse>>> getProductsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> getProductDetailState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> getSaveState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getDeleteState() {
        return null;
    }
    
    public final void loadProducts() {
    }
    
    public final void loadProductDetail(long id) {
    }
    
    public final void createProduct(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.product.CreateProductRequest request) {
    }
    
    public final void updateProduct(long id, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.product.UpdateProductRequest request) {
    }
    
    public final void deleteProduct(long id) {
    }
}