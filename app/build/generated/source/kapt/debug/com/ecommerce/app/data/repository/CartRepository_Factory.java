package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.CartApiService;
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
public final class CartRepository_Factory implements Factory<CartRepository> {
  private final Provider<CartApiService> apiProvider;

  public CartRepository_Factory(Provider<CartApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public CartRepository get() {
    return newInstance(apiProvider.get());
  }

  public static CartRepository_Factory create(Provider<CartApiService> apiProvider) {
    return new CartRepository_Factory(apiProvider);
  }

  public static CartRepository newInstance(CartApiService api) {
    return new CartRepository(api);
  }
}
