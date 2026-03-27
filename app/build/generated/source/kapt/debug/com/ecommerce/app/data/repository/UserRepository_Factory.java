package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.UserApiService;
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
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<UserApiService> apiProvider;

  public UserRepository_Factory(Provider<UserApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(apiProvider.get());
  }

  public static UserRepository_Factory create(Provider<UserApiService> apiProvider) {
    return new UserRepository_Factory(apiProvider);
  }

  public static UserRepository newInstance(UserApiService api) {
    return new UserRepository(api);
  }
}
