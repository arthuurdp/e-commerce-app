package com.ecommerce.app.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.ecommerce.app.R;
import com.google.android.material.textfield.TextInputLayout;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a1\u0010\b\u001a\u0004\u0018\u0001H\t\"\b\b\u0000\u0010\t*\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000e\u00a2\u0006\u0002\u0010\u000f\u001a\n\u0010\u0010\u001a\u00020\u0001*\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\u0001*\u00020\u0013\u001a\n\u0010\u0014\u001a\u00020\u0001*\u00020\u0011\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007\u001a\n\u0010\u0016\u001a\u00020\u0007*\u00020\u0017\u00a8\u0006\u0018"}, d2 = {"setFieldError", "", "context", "Landroid/content/Context;", "layout", "Lcom/google/android/material/textfield/TextInputLayout;", "message", "", "getParcelableCompat", "T", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "key", "clazz", "Ljava/lang/Class;", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/Class;)Landroid/os/Parcelable;", "hide", "Landroid/view/View;", "hideKeyboard", "Landroidx/fragment/app/Fragment;", "show", "showToast", "toCurrency", "", "app_debug"})
public final class ExtensionsKt {
    
    public static final void show(@org.jetbrains.annotations.NotNull()
    android.view.View $this$show) {
    }
    
    public static final void hide(@org.jetbrains.annotations.NotNull()
    android.view.View $this$hide) {
    }
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public static final void hideKeyboard(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$hideKeyboard) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final <T extends android.os.Parcelable>T getParcelableCompat(@org.jetbrains.annotations.NotNull()
    android.os.Bundle $this$getParcelableCompat, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Class<T> clazz) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toCurrency(double $this$toCurrency) {
        return null;
    }
    
    public static final void setFieldError(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.material.textfield.TextInputLayout layout, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
}