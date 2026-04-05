package com.ecommerce.app.ui.customer.cart;

import com.ecommerce.app.data.repository.AddressRepository;
import com.ecommerce.app.data.repository.CartRepository;
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
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<AddressRepository> addressRepositoryProvider;

  public CartViewModel_Factory(Provider<CartRepository> cartRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider) {
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.addressRepositoryProvider = addressRepositoryProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(cartRepositoryProvider.get(), addressRepositoryProvider.get());
  }

  public static CartViewModel_Factory create(Provider<CartRepository> cartRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider) {
    return new CartViewModel_Factory(cartRepositoryProvider, addressRepositoryProvider);
  }

  public static CartViewModel newInstance(CartRepository cartRepository,
      AddressRepository addressRepository) {
    return new CartViewModel(cartRepository, addressRepository);
  }
}
