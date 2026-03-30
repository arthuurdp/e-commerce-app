package com.ecommerce.app.ui.customer.profile.security;

import com.ecommerce.app.data.repository.EmailRepository;
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
public final class SecurityViewModel_Factory implements Factory<SecurityViewModel> {
  private final Provider<EmailRepository> emailRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public SecurityViewModel_Factory(Provider<EmailRepository> emailRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.emailRepositoryProvider = emailRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SecurityViewModel get() {
    return newInstance(emailRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static SecurityViewModel_Factory create(Provider<EmailRepository> emailRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new SecurityViewModel_Factory(emailRepositoryProvider, userRepositoryProvider);
  }

  public static SecurityViewModel newInstance(EmailRepository emailRepository,
      UserRepository userRepository) {
    return new SecurityViewModel(emailRepository, userRepository);
  }
}
