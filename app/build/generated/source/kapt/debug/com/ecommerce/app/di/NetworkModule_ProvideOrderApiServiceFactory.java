package com.ecommerce.app.di;

import com.ecommerce.app.data.api.OrderApiService;
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
public final class NetworkModule_ProvideOrderApiServiceFactory implements Factory<OrderApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideOrderApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public OrderApiService get() {
    return provideOrderApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideOrderApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideOrderApiServiceFactory(retrofitProvider);
  }

  public static OrderApiService provideOrderApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOrderApiService(retrofit));
  }
}
