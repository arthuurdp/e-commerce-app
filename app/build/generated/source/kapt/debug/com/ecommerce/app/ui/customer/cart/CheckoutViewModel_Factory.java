package com.ecommerce.app.ui.customer.cart;

import com.ecommerce.app.data.repository.AddressRepository;
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
public final class CheckoutViewModel_Factory implements Factory<CheckoutViewModel> {
  private final Provider<OrderRepository> orderRepositoryProvider;

  private final Provider<AddressRepository> addressRepositoryProvider;

  public CheckoutViewModel_Factory(Provider<OrderRepository> orderRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
    this.addressRepositoryProvider = addressRepositoryProvider;
  }

  @Override
  public CheckoutViewModel get() {
    return newInstance(orderRepositoryProvider.get(), addressRepositoryProvider.get());
  }

  public static CheckoutViewModel_Factory create(Provider<OrderRepository> orderRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider) {
    return new CheckoutViewModel_Factory(orderRepositoryProvider, addressRepositoryProvider);
  }

  public static CheckoutViewModel newInstance(OrderRepository orderRepository,
      AddressRepository addressRepository) {
    return new CheckoutViewModel(orderRepository, addressRepository);
  }
}
