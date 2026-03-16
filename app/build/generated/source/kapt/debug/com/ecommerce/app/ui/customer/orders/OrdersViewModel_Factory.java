package com.ecommerce.app.ui.customer.orders;

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
public final class OrdersViewModel_Factory implements Factory<OrdersViewModel> {
  private final Provider<OrderRepository> orderRepositoryProvider;

  public OrdersViewModel_Factory(Provider<OrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public OrdersViewModel get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static OrdersViewModel_Factory create(Provider<OrderRepository> orderRepositoryProvider) {
    return new OrdersViewModel_Factory(orderRepositoryProvider);
  }

  public static OrdersViewModel newInstance(OrderRepository orderRepository) {
    return new OrdersViewModel(orderRepository);
  }
}
