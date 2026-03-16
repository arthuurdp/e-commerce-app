package com.ecommerce.app.ui.customer.address;

import com.ecommerce.app.data.repository.AddressRepository;
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
public final class AddAddressViewModel_Factory implements Factory<AddAddressViewModel> {
  private final Provider<AddressRepository> addressRepositoryProvider;

  public AddAddressViewModel_Factory(Provider<AddressRepository> addressRepositoryProvider) {
    this.addressRepositoryProvider = addressRepositoryProvider;
  }

  @Override
  public AddAddressViewModel get() {
    return newInstance(addressRepositoryProvider.get());
  }

  public static AddAddressViewModel_Factory create(
      Provider<AddressRepository> addressRepositoryProvider) {
    return new AddAddressViewModel_Factory(addressRepositoryProvider);
  }

  public static AddAddressViewModel newInstance(AddressRepository addressRepository) {
    return new AddAddressViewModel(addressRepository);
  }
}
