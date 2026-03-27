package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.CategoryApiService;
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
public final class CategoryRepository_Factory implements Factory<CategoryRepository> {
  private final Provider<CategoryApiService> apiProvider;

  public CategoryRepository_Factory(Provider<CategoryApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public CategoryRepository get() {
    return newInstance(apiProvider.get());
  }

  public static CategoryRepository_Factory create(Provider<CategoryApiService> apiProvider) {
    return new CategoryRepository_Factory(apiProvider);
  }

  public static CategoryRepository newInstance(CategoryApiService api) {
    return new CategoryRepository(api);
  }
}
