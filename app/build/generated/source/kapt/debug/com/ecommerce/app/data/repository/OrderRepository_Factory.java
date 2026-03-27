package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.OrderApiService;
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
public final class OrderRepository_Factory implements Factory<OrderRepository> {
  private final Provider<OrderApiService> apiProvider;

  public OrderRepository_Factory(Provider<OrderApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public OrderRepository get() {
    return newInstance(apiProvider.get());
  }

  public static OrderRepository_Factory create(Provider<OrderApiService> apiProvider) {
    return new OrderRepository_Factory(apiProvider);
  }

  public static OrderRepository newInstance(OrderApiService api) {
    return new OrderRepository(api);
  }
}
