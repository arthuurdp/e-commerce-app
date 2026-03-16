package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
  private final Provider<ApiService> apiProvider;

  public CategoryRepository_Factory(Provider<ApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public CategoryRepository get() {
    return newInstance(apiProvider.get());
  }

  public static CategoryRepository_Factory create(Provider<ApiService> apiProvider) {
    return new CategoryRepository_Factory(apiProvider);
  }

  public static CategoryRepository newInstance(ApiService api) {
    return new CategoryRepository(api);
  }
}
