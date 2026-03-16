package com.ecommerce.app.ui.admin.orders;

import com.ecommerce.app.data.repository.OrderRepository;
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
public final class AdminOrdersViewModel_Factory implements Factory<AdminOrdersViewModel> {
  private final Provider<OrderRepository> orderRepositoryProvider;

  public AdminOrdersViewModel_Factory(Provider<OrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public AdminOrdersViewModel get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static AdminOrdersViewModel_Factory create(
      Provider<OrderRepository> orderRepositoryProvider) {
    return new AdminOrdersViewModel_Factory(orderRepositoryProvider);
  }

  public static AdminOrdersViewModel newInstance(OrderRepository orderRepository) {
    return new AdminOrdersViewModel(orderRepository);
  }
}
