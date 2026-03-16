package com.ecommerce.app.ui.admin.users;

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
public final class AdminUsersViewModel_Factory implements Factory<AdminUsersViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public AdminUsersViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public AdminUsersViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static AdminUsersViewModel_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new AdminUsersViewModel_Factory(userRepositoryProvider);
  }

  public static AdminUsersViewModel newInstance(UserRepository userRepository) {
    return new AdminUsersViewModel(userRepository);
  }
}
