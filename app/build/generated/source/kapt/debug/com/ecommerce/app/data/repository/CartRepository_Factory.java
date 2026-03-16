package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class CartRepository_Factory implements Factory<CartRepository> {
  private final Provider<ApiService> apiProvider;

  public CartRepository_Factory(Provider<ApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public CartRepository get() {
    return newInstance(apiProvider.get());
  }

  public static CartRepository_Factory create(Provider<ApiService> apiProvider) {
    return new CartRepository_Factory(apiProvider);
  }

  public static CartRepository newInstance(ApiService api) {
    return new CartRepository(api);
  }
}
