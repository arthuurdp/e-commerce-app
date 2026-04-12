package com.ecommerce.app.ui.shared;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.ecommerce.app.R;
import com.ecommerce.app.databinding.FragmentEnterCodeBinding;
import com.ecommerce.app.ui.auth.AuthViewModel;
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Locale;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\"H\u0002J\b\u0010#\u001a\u00020 H\u0002J$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020 H\u0016J\u001a\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010/\u001a\u00020 H\u0002J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u00020 H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0016\u0010\u0013R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/ecommerce/app/ui/shared/EnterCodeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ecommerce/app/databinding/FragmentEnterCodeBinding;", "authViewModel", "Lcom/ecommerce/app/ui/auth/AuthViewModel;", "getAuthViewModel", "()Lcom/ecommerce/app/ui/auth/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "binding", "getBinding", "()Lcom/ecommerce/app/databinding/FragmentEnterCodeBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "email", "", "getEmail", "()Ljava/lang/String;", "email$delegate", "mode", "getMode", "mode$delegate", "securityViewModel", "Lcom/ecommerce/app/ui/customer/profile/security/SecurityViewModel;", "getSecurityViewModel", "()Lcom/ecommerce/app/ui/customer/profile/security/SecurityViewModel;", "securityViewModel$delegate", "timerDuration", "", "handleResult", "", "result", "Lcom/ecommerce/app/util/NetworkResult;", "observeState", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "resendCode", "setupUI", "startResendTimer", "app_debug"})
public final class EnterCodeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.ecommerce.app.databinding.FragmentEnterCodeBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy securityViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy mode$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy email$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    private final long timerDuration = 60000L;
    
    public EnterCodeFragment() {
        super();
    }
    
    private final com.ecommerce.app.databinding.FragmentEnterCodeBinding getBinding() {
        return null;
    }
    
    private final com.ecommerce.app.ui.auth.AuthViewModel getAuthViewModel() {
        return null;
    }
    
    private final com.ecommerce.app.ui.customer.profile.security.SecurityViewModel getSecurityViewModel() {
        return null;
    }
    
    private final java.lang.String getMode() {
        return null;
    }
    
    private final java.lang.String getEmail() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void resendCode() {
    }
    
    private final void startResendTimer() {
    }
    
    private final void observeState() {
    }
    
    private final void handleResult(com.ecommerce.app.util.NetworkResult<java.lang.String> result) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}