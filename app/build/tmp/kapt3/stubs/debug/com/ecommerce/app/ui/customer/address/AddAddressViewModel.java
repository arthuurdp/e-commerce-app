package com.ecommerce.app.ui.customer.address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.address.CepLookupResponse;
import com.ecommerce.app.data.model.address.CreateAddressRequest;
import com.ecommerce.app.data.repository.AddressRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/ecommerce/app/ui/customer/address/AddAddressViewModel;", "Landroidx/lifecycle/ViewModel;", "addressRepository", "Lcom/ecommerce/app/data/repository/AddressRepository;", "(Lcom/ecommerce/app/data/repository/AddressRepository;)V", "_cepState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/address/CepLookupResponse;", "_saveState", "cepState", "Landroidx/lifecycle/LiveData;", "getCepState", "()Landroidx/lifecycle/LiveData;", "saveState", "getSaveState", "lookupCep", "", "cep", "", "saveAddress", "request", "Lcom/ecommerce/app/data/model/address/CreateAddressRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AddAddressViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.AddressRepository addressRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.CepLookupResponse>> _cepState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.CepLookupResponse>> cepState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<?>> _saveState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<?>> saveState = null;
    
    @javax.inject.Inject()
    public AddAddressViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.AddressRepository addressRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.CepLookupResponse>> getCepState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<?>> getSaveState() {
        return null;
    }
    
    public final void lookupCep(@org.jetbrains.annotations.NotNull()
    java.lang.String cep) {
    }
    
    public final void saveAddress(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.CreateAddressRequest request) {
    }
}