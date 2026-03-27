package com.ecommerce.app.ui.customer.profile;

import com.ecommerce.app.data.repository.EmailRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.TokenManager;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<EmailRepository> emailRepositoryProvider;

  private final Provider<TokenManager> tokenManagerProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<EmailRepository> emailRepositoryProvider,
      Provider<TokenManager> tokenManagerProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.emailRepositoryProvider = emailRepositoryProvider;
    this.tokenManagerProvider = tokenManagerProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepositoryProvider.get(), emailRepositoryProvider.get(), tokenManagerProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepositoryProvider,
      Provider<EmailRepository> emailRepositoryProvider,
      Provider<TokenManager> tokenManagerProvider) {
    return new ProfileViewModel_Factory(userRepositoryProvider, emailRepositoryProvider, tokenManagerProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepository,
      EmailRepository emailRepository, TokenManager tokenManager) {
    return new ProfileViewModel(userRepository, emailRepository, tokenManager);
  }
}
