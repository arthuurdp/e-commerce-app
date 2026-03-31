package com.ecommerce.app.ui.customer.search;

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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<ProductRepository> productRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  public SearchViewModel_Factory(Provider<ProductRepository> productRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(productRepositoryProvider.get(), categoryRepositoryProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<ProductRepository> productRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    return new SearchViewModel_Factory(productRepositoryProvider, categoryRepositoryProvider);
  }

  public static SearchViewModel newInstance(ProductRepository productRepository,
      CategoryRepository categoryRepository) {
    return new SearchViewModel(productRepository, categoryRepository);
  }
}
