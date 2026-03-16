package com.ecommerce.app.ui.admin.dashboard;

import com.ecommerce.app.data.repository.OrderRepository;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.data.repository.UserRepository;
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
public final class AdminDashboardViewModel_Factory implements Factory<AdminDashboardViewModel> {
  private final Provider<ProductRepository> productRepositoryProvider;

  private final Provider<OrderRepository> orderRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public AdminDashboardViewModel_Factory(Provider<ProductRepository> productRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
    this.orderRepositoryProvider = orderRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public AdminDashboardViewModel get() {
    return newInstance(productRepositoryProvider.get(), orderRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static AdminDashboardViewModel_Factory create(
      Provider<ProductRepository> productRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new AdminDashboardViewModel_Factory(productRepositoryProvider, orderRepositoryProvider, userRepositoryProvider);
  }

  public static AdminDashboardViewModel newInstance(ProductRepository productRepository,
      OrderRepository orderRepository, UserRepository userRepository) {
    return new AdminDashboardViewModel(productRepository, orderRepository, userRepository);
  }
}
