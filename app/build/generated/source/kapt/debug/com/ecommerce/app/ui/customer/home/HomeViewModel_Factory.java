package com.ecommerce.app.ui.customer.home;

import com.ecommerce.app.data.repository.CategoryRepository;
import com.ecommerce.app.data.repository.ProductRepository;
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

  public HomeViewModel_Factory(Provider<ProductRepository> productRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(productRepositoryProvider.get(), categoryRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<ProductRepository> productRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    return new HomeViewModel_Factory(productRepositoryProvider, categoryRepositoryProvider);
  }

  public static HomeViewModel newInstance(ProductRepository productRepository,
      CategoryRepository categoryRepository) {
    return new HomeViewModel(productRepository, categoryRepository);
  }
}
