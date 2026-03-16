package com.ecommerce.app.ui.admin.categories;

import com.ecommerce.app.data.repository.CategoryRepository;
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
public final class AdminCategoriesViewModel_Factory implements Factory<AdminCategoriesViewModel> {
  private final Provider<CategoryRepository> categoryRepositoryProvider;

  public AdminCategoriesViewModel_Factory(Provider<CategoryRepository> categoryRepositoryProvider) {
    this.categoryRepositoryProvider = categoryRepositoryProvider;
  }

  @Override
  public AdminCategoriesViewModel get() {
    return newInstance(categoryRepositoryProvider.get());
  }

  public static AdminCategoriesViewModel_Factory create(
      Provider<CategoryRepository> categoryRepositoryProvider) {
    return new AdminCategoriesViewModel_Factory(categoryRepositoryProvider);
  }

  public static AdminCategoriesViewModel newInstance(CategoryRepository categoryRepository) {
    return new AdminCategoriesViewModel(categoryRepository);
  }
}
