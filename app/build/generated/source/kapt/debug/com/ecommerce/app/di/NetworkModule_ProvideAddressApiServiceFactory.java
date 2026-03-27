package com.ecommerce.app.di;

import com.ecommerce.app.data.api.AddressApiService;
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
public final class NetworkModule_ProvideAddressApiServiceFactory implements Factory<AddressApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideAddressApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AddressApiService get() {
    return provideAddressApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideAddressApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideAddressApiServiceFactory(retrofitProvider);
  }

  public static AddressApiService provideAddressApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAddressApiService(retrofit));
  }
}
