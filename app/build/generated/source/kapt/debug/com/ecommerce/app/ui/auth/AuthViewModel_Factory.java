package com.ecommerce.app.ui.auth;

import com.ecommerce.app.data.repository.AuthRepository;
import com.ecommerce.app.data.repository.EmailRepository;
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<EmailRepository> emailRepositoryProvider;

  private final Provider<TokenManager> tokenManagerProvider;

  public AuthViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<EmailRepository> emailRepositoryProvider,
      Provider<TokenManager> tokenManagerProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.emailRepositoryProvider = emailRepositoryProvider;
    this.tokenManagerProvider = tokenManagerProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), emailRepositoryProvider.get(), tokenManagerProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<EmailRepository> emailRepositoryProvider,
      Provider<TokenManager> tokenManagerProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, emailRepositoryProvider, tokenManagerProvider);
  }

  public static AuthViewModel newInstance(AuthRepository authRepository,
      EmailRepository emailRepository, TokenManager tokenManager) {
    return new AuthViewModel(authRepository, emailRepository, tokenManager);
  }
}
