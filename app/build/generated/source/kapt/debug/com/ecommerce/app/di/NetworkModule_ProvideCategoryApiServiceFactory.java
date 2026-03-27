package com.ecommerce.app.di;

import com.ecommerce.app.data.api.CategoryApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideCategoryApiServiceFactory implements Factory<CategoryApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideCategoryApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CategoryApiService get() {
    return provideCategoryApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideCategoryApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideCategoryApiServiceFactory(retrofitProvider);
  }

  public static CategoryApiService provideCategoryApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCategoryApiService(retrofit));
  }
}
