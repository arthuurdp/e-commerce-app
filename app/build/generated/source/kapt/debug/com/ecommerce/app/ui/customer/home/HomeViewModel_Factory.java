package com.ecommerce.app.ui.customer.home;

import com.ecommerce.app.data.repository.CartRepository;
import com.ecommerce.app.data.repository.CategoryRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<ProductRepository> productRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  public HomeViewModel_Factory(Provider<ProductRepository> productRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(productRepositoryProvider.get(), categoryRepositoryProvider.get(), userRepositoryProvider.get(), cartRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<ProductRepository> productRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    return new HomeViewModel_Factory(productRepositoryProvider, categoryRepositoryProvider, userRepositoryProvider, cartRepositoryProvider);
  }

  public static HomeViewModel newInstance(ProductRepository productRepository,
      CategoryRepository categoryRepository, UserRepository userRepository,
      CartRepository cartRepository) {
    return new HomeViewModel(productRepository, categoryRepository, userRepository, cartRepository);
  }
}
