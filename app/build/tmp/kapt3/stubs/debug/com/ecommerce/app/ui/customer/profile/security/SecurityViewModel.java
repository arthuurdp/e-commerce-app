package com.ecommerce.app.ui.customer.profile.security;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.repository.EmailRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\nJ\u000e\u0010*\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\nJ\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\nJ\u0006\u0010,\u001a\u00020\u000eJ\u000e\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\nJ\u000e\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\nJ\u0006\u00101\u001a\u00020\u000eJ\u000e\u00102\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\nJ\u000e\u00104\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\nJ$\u00105\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n060\tH\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0017\u00a8\u00067"}, d2 = {"Lcom/ecommerce/app/ui/customer/profile/security/SecurityViewModel;", "Landroidx/lifecycle/ViewModel;", "emailRepository", "Lcom/ecommerce/app/data/repository/EmailRepository;", "userRepository", "Lcom/ecommerce/app/data/repository/UserRepository;", "(Lcom/ecommerce/app/data/repository/EmailRepository;Lcom/ecommerce/app/data/repository/UserRepository;)V", "_confirmEmailChangeState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "", "_confirmEmailState", "_confirmPasswordChangeState", "_deleteState", "", "_requestEmailChangeState", "_requestPasswordChangeState", "_resetPasswordState", "_sendEmailVerificationState", "_verifyResetCodeState", "confirmEmailChangeState", "Landroidx/lifecycle/LiveData;", "getConfirmEmailChangeState", "()Landroidx/lifecycle/LiveData;", "confirmEmailState", "getConfirmEmailState", "confirmPasswordChangeState", "getConfirmPasswordChangeState", "deleteState", "getDeleteState", "requestEmailChangeState", "getRequestEmailChangeState", "requestPasswordChangeState", "getRequestPasswordChangeState", "resetPasswordState", "getResetPasswordState", "sendEmailVerificationState", "getSendEmailVerificationState", "verifyResetCodeState", "getVerifyResetCodeState", "confirmEmail", "code", "confirmEmailChange", "confirmPasswordChange", "deleteAccount", "requestEmailChange", "email", "requestPasswordChange", "password", "sendEmailVerification", "setNewPassword", "newPassword", "verifyForgotPasswordCode", "toStringResult", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SecurityViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.EmailRepository emailRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> _deleteState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> deleteState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _sendEmailVerificationState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> sendEmailVerificationState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _confirmEmailState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> confirmEmailState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _requestEmailChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> requestEmailChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _confirmEmailChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> confirmEmailChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _requestPasswordChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> requestPasswordChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _confirmPasswordChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> confirmPasswordChangeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _verifyResetCodeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> verifyResetCodeState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _resetPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> resetPasswordState = null;
    
    @javax.inject.Inject()
    public SecurityViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.EmailRepository emailRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getDeleteState() {
        return null;
    }
    
    public final void deleteAccount() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getSendEmailVerificationState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getConfirmEmailState() {
        return null;
    }
    
    public final void sendEmailVerification() {
    }
    
    public final void confirmEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getRequestEmailChangeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getConfirmEmailChangeState() {
        return null;
    }
    
    public final void requestEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void confirmEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getRequestPasswordChangeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getConfirmPasswordChangeState() {
        return null;
    }
    
    public final void requestPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void confirmPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getVerifyResetCodeState() {
        return null;
    }
    
    public final void verifyForgotPasswordCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getResetPasswordState() {
        return null;
    }
    
    public final void setNewPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
    }
    
    private final com.ecommerce.app.util.NetworkResult<java.lang.String> toStringResult(com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>> $this$toStringResult) {
        return null;
    }
}