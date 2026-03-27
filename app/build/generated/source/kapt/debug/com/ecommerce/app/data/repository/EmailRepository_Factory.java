package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.EmailApiService;
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
public final class EmailRepository_Factory implements Factory<EmailRepository> {
  private final Provider<EmailApiService> apiProvider;

  public EmailRepository_Factory(Provider<EmailApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public EmailRepository get() {
    return newInstance(apiProvider.get());
  }

  public static EmailRepository_Factory create(Provider<EmailApiService> apiProvider) {
    return new EmailRepository_Factory(apiProvider);
  }

  public static EmailRepository newInstance(EmailApiService api) {
    return new EmailRepository(api);
  }
}
