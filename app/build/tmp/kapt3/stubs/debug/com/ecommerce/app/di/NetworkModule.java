package com.ecommerce.app.di;

import com.ecommerce.app.BuildConfig;
import com.ecommerce.app.data.api.AddressApiService;
import com.ecommerce.app.data.api.AuthApiService;
import com.ecommerce.app.data.api.AuthInterceptor;
import com.ecommerce.app.data.api.CartApiService;
import com.ecommerce.app.data.api.CategoryApiService;
import com.ecommerce.app.data.api.EmailApiService;
import com.ecommerce.app.data.api.OrderApiService;
import com.ecommerce.app.data.api.ProductApiService;
import com.ecommerce.app.data.api.UserApiService;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0012H\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u001e"}, d2 = {"Lcom/ecommerce/app/di/NetworkModule;", "", "()V", "provideAddressApiService", "Lcom/ecommerce/app/data/api/AddressApiService;", "retrofit", "Lretrofit2/Retrofit;", "provideAuthApiService", "Lcom/ecommerce/app/data/api/AuthApiService;", "provideCartApiService", "Lcom/ecommerce/app/data/api/CartApiService;", "provideCategoryApiService", "Lcom/ecommerce/app/data/api/CategoryApiService;", "provideEmailApiService", "Lcom/ecommerce/app/data/api/EmailApiService;", "provideLoggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "authInterceptor", "Lcom/ecommerce/app/data/api/AuthInterceptor;", "loggingInterceptor", "provideOrderApiService", "Lcom/ecommerce/app/data/api/OrderApiService;", "provideProductApiService", "Lcom/ecommerce/app/data/api/ProductApiService;", "provideRetrofit", "client", "provideUserApiService", "Lcom/ecommerce/app/data/api/UserApiService;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.ecommerce.app.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.logging.HttpLoggingInterceptor provideLoggingInterceptor() {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOkHttpClient(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.AuthInterceptor authInterceptor, @org.jetbrains.annotations.NotNull()
    okhttp3.logging.HttpLoggingInterceptor loggingInterceptor) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideRetrofit(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient client) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.AuthApiService provideAuthApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.UserApiService provideUserApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.CartApiService provideCartApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.AddressApiService provideAddressApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.CategoryApiService provideCategoryApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.EmailApiService provideEmailApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.OrderApiService provideOrderApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.api.ProductApiService provideProductApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
}