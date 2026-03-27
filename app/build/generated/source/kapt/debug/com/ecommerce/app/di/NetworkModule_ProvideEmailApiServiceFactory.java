package com.ecommerce.app.di;

import com.ecommerce.app.data.api.EmailApiService;
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
public final class NetworkModule_ProvideEmailApiServiceFactory implements Factory<EmailApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideEmailApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public EmailApiService get() {
    return provideEmailApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideEmailApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideEmailApiServiceFactory(retrofitProvider);
  }

  public static EmailApiService provideEmailApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideEmailApiService(retrofit));
  }
}
