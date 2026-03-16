package com.ecommerce.app.ui.admin.products;

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
public final class AdminProductsViewModel_Factory implements Factory<AdminProductsViewModel> {
  private final Provider<ProductRepository> productRepositoryProvider;

  public AdminProductsViewModel_Factory(Provider<ProductRepository> productRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
  }

  @Override
  public AdminProductsViewModel get() {
    return newInstance(productRepositoryProvider.get());
  }

  public static AdminProductsViewModel_Factory create(
      Provider<ProductRepository> productRepositoryProvider) {
    return new AdminProductsViewModel_Factory(productRepositoryProvider);
  }

  public static AdminProductsViewModel newInstance(ProductRepository productRepository) {
    return new AdminProductsViewModel(productRepository);
  }
}
