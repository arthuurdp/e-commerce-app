package com.ecommerce.app.util;

import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.google.android.material.snackbar.Snackbar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a-\u0010\u0004\u001a\u00020\u0001*\u00020\u00052\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u00a2\u0006\u0002\u0010\n\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u001a(\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011\u001a\n\u0010\u001a\u001a\u00020\u0011*\u00020\u001b\u00a8\u0006\u001c"}, d2 = {"hide", "", "Landroid/view/View;", "invisible", "launchOnStarted", "Landroidx/fragment/app/Fragment;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/fragment/app/Fragment;Lkotlin/jvm/functions/Function1;)V", "show", "showIf", "condition", "", "showLongToast", "message", "", "showSnackbar", "duration", "", "showSnackbarWithAction", "actionText", "action", "Lkotlin/Function0;", "showToast", "toCurrency", "", "app_debug"})
public final class ExtensionsKt {
    
    public static final void show(@org.jetbrains.annotations.NotNull()
    android.view.View $this$show) {
    }
    
    public static final void hide(@org.jetbrains.annotations.NotNull()
    android.view.View $this$hide) {
    }
    
    public static final void invisible(@org.jetbrains.annotations.NotNull()
    android.view.View $this$invisible) {
    }
    
    public static final void showIf(@org.jetbrains.annotations.NotNull()
    android.view.View $this$showIf, boolean condition) {
    }
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public static final void showLongToast(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showLongToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public static final void showSnackbar(@org.jetbrains.annotations.NotNull()
    android.view.View $this$showSnackbar, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int duration) {
    }
    
    public static final void showSnackbarWithAction(@org.jetbrains.annotations.NotNull()
    android.view.View $this$showSnackbarWithAction, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String actionText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> action) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toCurrency(double $this$toCurrency) {
        return null;
    }
    
    public static final void launchOnStarted(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$launchOnStarted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
    }
}