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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.app.BuildConfig;
import com.ecommerce.app.R;
import com.google.android.material.textfield.TextInputLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\t\u001a\f\u0010\n\u001a\u00020\u0007*\u0004\u0018\u00010\u0007\u001a1\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\b\b\u0000\u0010\f*\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u0011\u00a2\u0006\u0002\u0010\u0012\u001a\n\u0010\u0013\u001a\u00020\u0001*\u00020\u0014\u001a\n\u0010\u0015\u001a\u00020\u0001*\u00020\u0016\u001a\n\u0010\u0017\u001a\u00020\u0001*\u00020\u0014\u001a\u0012\u0010\u0018\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u0007\u001a\n\u0010\u0019\u001a\u00020\u0007*\u00020\u001a\u001a\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u0007*\u0004\u0018\u00010\u0007\u00a8\u0006\u001c"}, d2 = {"setFieldError", "", "context", "Landroid/content/Context;", "layout", "Lcom/google/android/material/textfield/TextInputLayout;", "message", "", "addDivider", "Landroidx/recyclerview/widget/RecyclerView;", "formatDate", "getParcelableCompat", "T", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "key", "clazz", "Ljava/lang/Class;", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/Class;)Landroid/os/Parcelable;", "hide", "Landroid/view/View;", "hideKeyboard", "Landroidx/fragment/app/Fragment;", "show", "showToast", "toCurrency", "", "toImageUrl", "app_debug"})
public final class ExtensionsKt {
    
    public static final void show(@org.jetbrains.annotations.NotNull()
    android.view.View $this$show) {
    }
    
    public static final void hide(@org.jetbrains.annotations.NotNull()
    android.view.View $this$hide) {
    }
    
    public static final void addDivider(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView $this$addDivider) {
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
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String toImageUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String $this$toImageUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDate(@org.jetbrains.annotations.Nullable()
    java.lang.String $this$formatDate) {
        return null;
    }
    
    public static final void setFieldError(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.material.textfield.TextInputLayout layout, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
}