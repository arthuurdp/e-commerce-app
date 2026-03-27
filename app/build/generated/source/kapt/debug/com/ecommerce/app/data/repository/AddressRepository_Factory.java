package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.AddressApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AddressRepository_Factory implements Factory<AddressRepository> {
  private final Provider<AddressApiService> apiProvider;

  public AddressRepository_Factory(Provider<AddressApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public AddressRepository get() {
    return newInstance(apiProvider.get());
  }

  public static AddressRepository_Factory create(Provider<AddressApiService> apiProvider) {
    return new AddressRepository_Factory(apiProvider);
  }

  public static AddressRepository newInstance(AddressApiService api) {
    return new AddressRepository(api);
  }
}
