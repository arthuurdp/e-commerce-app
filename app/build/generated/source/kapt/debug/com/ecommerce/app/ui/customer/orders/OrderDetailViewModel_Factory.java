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
public final class OrderDetailViewModel_Factory implements Factory<OrderDetailViewModel> {
  private final Provider<OrderRepository> orderRepositoryProvider;

  public OrderDetailViewModel_Factory(Provider<OrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public OrderDetailViewModel get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static OrderDetailViewModel_Factory create(
      Provider<OrderRepository> orderRepositoryProvider) {
    return new OrderDetailViewModel_Factory(orderRepositoryProvider);
  }

  public static OrderDetailViewModel newInstance(OrderRepository orderRepository) {
    return new OrderDetailViewModel(orderRepository);
  }
}
