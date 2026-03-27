package com.ecommerce.app.di

import com.ecommerce.app.BuildConfig
import com.ecommerce.app.data.api.AddressApiService
import com.ecommerce.app.data.api.AuthApiService
import com.ecommerce.app.data.api.AuthInterceptor
import com.ecommerce.app.data.api.CartApiService
import com.ecommerce.app.data.api.CategoryApiService
import com.ecommerce.app.data.api.EmailApiService
import com.ecommerce.app.data.api.OrderApiService
import com.ecommerce.app.data.api.ProductApiService
import com.ecommerce.app.data.api.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Singleton
    @Provides
    fun provideCartApiService(retrofit: Retrofit): CartApiService =
        retrofit.create(CartApiService::class.java)

    @Singleton
    @Provides
    fun provideAddressApiService(retrofit: Retrofit): AddressApiService =
        retrofit.create(AddressApiService::class.java)

    @Singleton
    @Provides
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService =
        retrofit.create(CategoryApiService::class.java)

    @Singleton
    @Provides
    fun provideEmailApiService(retrofit: Retrofit): EmailApiService =
        retrofit.create(EmailApiService::class.java)

    @Singleton
    @Provides
    fun provideOrderApiService(retrofit: Retrofit): OrderApiService =
        retrofit.create(OrderApiService::class.java)

    @Singleton
    @Provides
    fun provideProductApiService(retrofit: Retrofit): ProductApiService =
        retrofit.create(ProductApiService::class.java)
}
