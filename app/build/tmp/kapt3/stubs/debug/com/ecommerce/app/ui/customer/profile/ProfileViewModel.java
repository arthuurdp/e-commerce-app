package com.ecommerce.app.ui.customer.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.user.UpdateUserRequest;
import com.ecommerce.app.data.model.user.UserResponse;
import com.ecommerce.app.data.repository.EmailRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.NetworkResult;
import com.ecommerce.app.util.TokenManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\'\n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\fJ\u000e\u00100\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\fJ\u000e\u00101\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\fJ\u0006\u00102\u001a\u00020\u0010J\u0006\u00103\u001a\u00020\u0010J\u0006\u00104\u001a\u00020\u0010J\u000e\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\fJ\u000e\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u00020\fJ\u0006\u00109\u001a\u00020\u0010J\u000e\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\fJ$\u0010<\u001a\u00020\u00102\b\u0010=\u001a\u0004\u0018\u00010\f2\b\u0010>\u001a\u0004\u0018\u00010\f2\b\u0010?\u001a\u0004\u0018\u00010\fJ$\u0010@\u001a\b\u0012\u0004\u0012\u00020\f0\u000b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0A0\u000bH\u0002R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001bR\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006B"}, d2 = {"Lcom/ecommerce/app/ui/customer/profile/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepository", "Lcom/ecommerce/app/data/repository/UserRepository;", "emailRepository", "Lcom/ecommerce/app/data/repository/EmailRepository;", "tokenManager", "Lcom/ecommerce/app/util/TokenManager;", "(Lcom/ecommerce/app/data/repository/UserRepository;Lcom/ecommerce/app/data/repository/EmailRepository;Lcom/ecommerce/app/util/TokenManager;)V", "_confirmEmailChangeState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "", "_confirmEmailState", "_confirmPasswordChangeState", "_deleteState", "", "_profileState", "Lcom/ecommerce/app/data/model/user/UserResponse;", "_requestEmailChangeState", "_requestPasswordChangeState", "_resetPasswordState", "_sendEmailVerificationState", "_updateState", "confirmEmailChangeState", "Landroidx/lifecycle/LiveData;", "getConfirmEmailChangeState", "()Landroidx/lifecycle/LiveData;", "confirmEmailState", "getConfirmEmailState", "confirmPasswordChangeState", "getConfirmPasswordChangeState", "deleteState", "getDeleteState", "profileState", "getProfileState", "requestEmailChangeState", "getRequestEmailChangeState", "requestPasswordChangeState", "getRequestPasswordChangeState", "resetPasswordState", "getResetPasswordState", "sendEmailVerificationState", "getSendEmailVerificationState", "updateState", "getUpdateState", "confirmEmail", "code", "confirmEmailChange", "confirmPasswordChange", "deleteAccount", "loadProfile", "logout", "requestEmailChange", "email", "requestPasswordChange", "password", "sendEmailVerification", "setNewPassword", "newPassword", "updateProfile", "firstName", "lastName", "phone", "toStringResult", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.EmailRepository emailRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.util.TokenManager tokenManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> _profileState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> profileState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> _updateState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> updateState = null;
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
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> _resetPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> resetPasswordState = null;
    
    @javax.inject.Inject()
    public ProfileViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.EmailRepository emailRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.util.TokenManager tokenManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> getProfileState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> getUpdateState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getDeleteState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getSendEmailVerificationState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getConfirmEmailState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getRequestEmailChangeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getConfirmEmailChangeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getRequestPasswordChangeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getConfirmPasswordChangeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.String>> getResetPasswordState() {
        return null;
    }
    
    public final void loadProfile() {
    }
    
    public final void updateProfile(@org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastName, @org.jetbrains.annotations.Nullable()
    java.lang.String phone) {
    }
    
    public final void deleteAccount() {
    }
    
    public final void sendEmailVerification() {
    }
    
    public final void confirmEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    public final void requestEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void confirmEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    public final void requestPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void confirmPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    public final void setNewPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
    }
    
    private final com.ecommerce.app.util.NetworkResult<java.lang.String> toStringResult(com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>> $this$toStringResult) {
        return null;
    }
    
    public final void logout() {
    }
}