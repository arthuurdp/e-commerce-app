package com.ecommerce.app.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowInsetsController;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.ecommerce.app.R;
import com.ecommerce.app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\tH\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006 "}, d2 = {"Lcom/ecommerce/app/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ecommerce/app/databinding/ActivityMainBinding;", "customerRootDestinations", "", "", "isKeyboardVisible", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/ecommerce/app/ui/MainViewModel;", "getViewModel", "()Lcom/ecommerce/app/ui/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "handleBottomNavVisibility", "", "currentDestinationId", "observeSession", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "setupBottomNav", "setupKeyboardListener", "updateBottomNavScale", "navView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "selectedId", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ecommerce.app.databinding.ActivityMainBinding binding;
    private androidx.navigation.NavController navController;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean isKeyboardVisible = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> customerRootDestinations = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.ecommerce.app.ui.MainViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupBottomNav() {
    }
    
    private final void handleBottomNavVisibility(int currentDestinationId) {
    }
    
    private final void setupKeyboardListener() {
    }
    
    private final void updateBottomNavScale(com.google.android.material.bottomnavigation.BottomNavigationView navView, int selectedId) {
    }
    
    private final void observeSession() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}