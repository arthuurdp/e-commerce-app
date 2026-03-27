package com.ecommerce.app.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.auth.LoginRequest;
import com.ecommerce.app.data.model.auth.RegisterRequest;
import com.ecommerce.app.data.repository.AuthRepository;
import com.ecommerce.app.data.repository.EmailRepository;
import com.ecommerce.app.util.NetworkResult;
import com.ecommerce.app.util.TokenManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\fJ\u0016\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\fJ\u0006\u0010\'\u001a\u00020\u000eJ\u000e\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\fR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0017@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015\u00a8\u0006-"}, d2 = {"Lcom/ecommerce/app/ui/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/ecommerce/app/data/repository/AuthRepository;", "emailRepository", "Lcom/ecommerce/app/data/repository/EmailRepository;", "tokenManager", "Lcom/ecommerce/app/util/TokenManager;", "(Lcom/ecommerce/app/data/repository/AuthRepository;Lcom/ecommerce/app/data/repository/EmailRepository;Lcom/ecommerce/app/util/TokenManager;)V", "_forgotPasswordState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "", "_loginState", "", "_registerState", "_resetPasswordState", "_verifyResetCodeState", "forgotPasswordState", "Landroidx/lifecycle/LiveData;", "getForgotPasswordState", "()Landroidx/lifecycle/LiveData;", "<set-?>", "", "isAdmin", "()Z", "loginState", "getLoginState", "registerState", "getRegisterState", "resetPasswordState", "getResetPasswordState", "verifyResetCodeState", "getVerifyResetCodeState", "forgotPassword", "email", "login", "credential", "password", "logout", "register", "request", "Lcom/ecommerce/app/data/model/auth/RegisterRequest;", "verifyResetCode", "code", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.EmailRepository emailRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.util.TokenManager tokenManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> _loginState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> loginState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> _registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _forgotPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> forgotPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _verifyResetCodeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> verifyResetCodeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _resetPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> resetPasswordState = null;
    private boolean isAdmin = false;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.EmailRepository emailRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.util.TokenManager tokenManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getLoginState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getRegisterState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getForgotPasswordState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getVerifyResetCodeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getResetPasswordState() {
        return null;
    }
    
    public final boolean isAdmin() {
        return false;
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String credential, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void register(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.RegisterRequest request) {
    }
    
    public final void forgotPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void verifyResetCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    public final void logout() {
    }
}