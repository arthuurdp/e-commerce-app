package com.ecommerce.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.api.AddressApiService;
import com.ecommerce.app.data.api.AuthApiService;
import com.ecommerce.app.data.api.AuthInterceptor;
import com.ecommerce.app.data.api.CartApiService;
import com.ecommerce.app.data.api.CategoryApiService;
import com.ecommerce.app.data.api.EmailApiService;
import com.ecommerce.app.data.api.OrderApiService;
import com.ecommerce.app.data.api.ProductApiService;
import com.ecommerce.app.data.api.UserApiService;
import com.ecommerce.app.data.repository.AddressRepository;
import com.ecommerce.app.data.repository.AuthRepository;
import com.ecommerce.app.data.repository.CartRepository;
import com.ecommerce.app.data.repository.CategoryRepository;
import com.ecommerce.app.data.repository.EmailRepository;
import com.ecommerce.app.data.repository.OrderRepository;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.di.NetworkModule_ProvideAddressApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideAuthApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideCartApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideCategoryApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideEmailApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideLoggingInterceptorFactory;
import com.ecommerce.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.ecommerce.app.di.NetworkModule_ProvideOrderApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideProductApiServiceFactory;
import com.ecommerce.app.di.NetworkModule_ProvideRetrofitFactory;
import com.ecommerce.app.di.NetworkModule_ProvideUserApiServiceFactory;
import com.ecommerce.app.ui.MainActivity;
import com.ecommerce.app.ui.MainViewModel;
import com.ecommerce.app.ui.MainViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.admin.categories.AdminCategoriesFragment;
import com.ecommerce.app.ui.admin.categories.AdminCategoriesViewModel;
import com.ecommerce.app.ui.admin.categories.AdminCategoriesViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.admin.dashboard.AdminDashboardFragment;
import com.ecommerce.app.ui.admin.dashboard.AdminDashboardViewModel;
import com.ecommerce.app.ui.admin.dashboard.AdminDashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.admin.orders.AdminOrdersFragment;
import com.ecommerce.app.ui.admin.orders.AdminOrdersViewModel;
import com.ecommerce.app.ui.admin.orders.AdminOrdersViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.admin.products.AdminEditProductFragment;
import com.ecommerce.app.ui.admin.products.AdminProductsFragment;
import com.ecommerce.app.ui.admin.products.AdminProductsViewModel;
import com.ecommerce.app.ui.admin.products.AdminProductsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.admin.users.AdminUsersFragment;
import com.ecommerce.app.ui.admin.users.AdminUsersViewModel;
import com.ecommerce.app.ui.admin.users.AdminUsersViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.auth.AuthViewModel;
import com.ecommerce.app.ui.auth.AuthViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.auth.LoginFragment;
import com.ecommerce.app.ui.auth.RegisterFragment;
import com.ecommerce.app.ui.auth.forgot_password.ForgotPasswordEnterEmailFragment;
import com.ecommerce.app.ui.auth.forgot_password.ForgotPasswordResetPasswordFragment;
import com.ecommerce.app.ui.customer.address.AddAddressFragment;
import com.ecommerce.app.ui.customer.address.AddAddressViewModel;
import com.ecommerce.app.ui.customer.address.AddAddressViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.address.AddressListFragment;
import com.ecommerce.app.ui.customer.address.AddressViewModel;
import com.ecommerce.app.ui.customer.address.AddressViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.cart.CartFragment;
import com.ecommerce.app.ui.customer.cart.CartViewModel;
import com.ecommerce.app.ui.customer.cart.CartViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.cart.CheckoutFragment;
import com.ecommerce.app.ui.customer.cart.CheckoutViewModel;
import com.ecommerce.app.ui.customer.cart.CheckoutViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.home.HomeFragment;
import com.ecommerce.app.ui.customer.home.HomeViewModel;
import com.ecommerce.app.ui.customer.home.HomeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.orders.OrderDetailFragment;
import com.ecommerce.app.ui.customer.orders.OrderDetailViewModel;
import com.ecommerce.app.ui.customer.orders.OrderDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.orders.OrdersFragment;
import com.ecommerce.app.ui.customer.orders.OrdersViewModel;
import com.ecommerce.app.ui.customer.orders.OrdersViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.products.ProductDetailFragment;
import com.ecommerce.app.ui.customer.products.ProductDetailViewModel;
import com.ecommerce.app.ui.customer.products.ProductDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.profile.EditProfileFragment;
import com.ecommerce.app.ui.customer.profile.ProfileFragment;
import com.ecommerce.app.ui.customer.profile.ProfileViewModel;
import com.ecommerce.app.ui.customer.profile.ProfileViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.profile.security.ChangeEmailEnterNewEmailFragment;
import com.ecommerce.app.ui.customer.profile.security.ChangePasswordEnterNewPasswordFragment;
import com.ecommerce.app.ui.customer.profile.security.SecurityFragment;
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel;
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.customer.search.SearchFragment;
import com.ecommerce.app.ui.customer.search.SearchViewModel;
import com.ecommerce.app.ui.customer.search.SearchViewModel_HiltModules_KeyModule_ProvideFactory;
import com.ecommerce.app.ui.shared.EnterCodeFragment;
import com.ecommerce.app.util.TokenManager;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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
public final class DaggerECommerceApp_HiltComponents_SingletonC {
  private DaggerECommerceApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ECommerceApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ECommerceApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ECommerceApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ECommerceApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ECommerceApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ECommerceApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ECommerceApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ECommerceApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ECommerceApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ECommerceApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ECommerceApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectAdminCategoriesFragment(AdminCategoriesFragment arg0) {
    }

    @Override
    public void injectAdminDashboardFragment(AdminDashboardFragment arg0) {
    }

    @Override
    public void injectAdminOrdersFragment(AdminOrdersFragment arg0) {
    }

    @Override
    public void injectAdminEditProductFragment(AdminEditProductFragment arg0) {
    }

    @Override
    public void injectAdminProductsFragment(AdminProductsFragment arg0) {
    }

    @Override
    public void injectAdminUsersFragment(AdminUsersFragment arg0) {
    }

    @Override
    public void injectLoginFragment(LoginFragment arg0) {
    }

    @Override
    public void injectRegisterFragment(RegisterFragment arg0) {
    }

    @Override
    public void injectForgotPasswordEnterEmailFragment(ForgotPasswordEnterEmailFragment arg0) {
    }

    @Override
    public void injectForgotPasswordResetPasswordFragment(
        ForgotPasswordResetPasswordFragment arg0) {
    }

    @Override
    public void injectAddAddressFragment(AddAddressFragment arg0) {
    }

    @Override
    public void injectAddressListFragment(AddressListFragment arg0) {
    }

    @Override
    public void injectCartFragment(CartFragment arg0) {
    }

    @Override
    public void injectCheckoutFragment(CheckoutFragment arg0) {
    }

    @Override
    public void injectHomeFragment(HomeFragment arg0) {
    }

    @Override
    public void injectOrderDetailFragment(OrderDetailFragment arg0) {
    }

    @Override
    public void injectOrdersFragment(OrdersFragment arg0) {
    }

    @Override
    public void injectProductDetailFragment(ProductDetailFragment arg0) {
    }

    @Override
    public void injectEditProfileFragment(EditProfileFragment arg0) {
    }

    @Override
    public void injectProfileFragment(ProfileFragment arg0) {
    }

    @Override
    public void injectChangeEmailEnterNewEmailFragment(ChangeEmailEnterNewEmailFragment arg0) {
    }

    @Override
    public void injectChangePasswordEnterNewPasswordFragment(
        ChangePasswordEnterNewPasswordFragment arg0) {
    }

    @Override
    public void injectSecurityFragment(SecurityFragment arg0) {
    }

    @Override
    public void injectSearchFragment(SearchFragment arg0) {
    }

    @Override
    public void injectEnterCodeFragment(EnterCodeFragment arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ECommerceApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ECommerceApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(18).add(AddAddressViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AddressViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AdminCategoriesViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AdminDashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AdminOrdersViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AdminProductsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AdminUsersViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AuthViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(CartViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(CheckoutViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(HomeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MainViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(OrderDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(OrdersViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ProductDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ProfileViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SearchViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SecurityViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends ECommerceApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddAddressViewModel> addAddressViewModelProvider;

    private Provider<AddressViewModel> addressViewModelProvider;

    private Provider<AdminCategoriesViewModel> adminCategoriesViewModelProvider;

    private Provider<AdminDashboardViewModel> adminDashboardViewModelProvider;

    private Provider<AdminOrdersViewModel> adminOrdersViewModelProvider;

    private Provider<AdminProductsViewModel> adminProductsViewModelProvider;

    private Provider<AdminUsersViewModel> adminUsersViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<CartViewModel> cartViewModelProvider;

    private Provider<CheckoutViewModel> checkoutViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<MainViewModel> mainViewModelProvider;

    private Provider<OrderDetailViewModel> orderDetailViewModelProvider;

    private Provider<OrdersViewModel> ordersViewModelProvider;

    private Provider<ProductDetailViewModel> productDetailViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<SearchViewModel> searchViewModelProvider;

    private Provider<SecurityViewModel> securityViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private AddressRepository addressRepository() {
      return new AddressRepository(singletonCImpl.provideAddressApiServiceProvider.get());
    }

    private CategoryRepository categoryRepository() {
      return new CategoryRepository(singletonCImpl.provideCategoryApiServiceProvider.get());
    }

    private ProductRepository productRepository() {
      return new ProductRepository(singletonCImpl.provideProductApiServiceProvider.get());
    }

    private OrderRepository orderRepository() {
      return new OrderRepository(singletonCImpl.provideOrderApiServiceProvider.get());
    }

    private UserRepository userRepository() {
      return new UserRepository(singletonCImpl.provideUserApiServiceProvider.get());
    }

    private CartRepository cartRepository() {
      return new CartRepository(singletonCImpl.provideCartApiServiceProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addAddressViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.addressViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.adminCategoriesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.adminDashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.adminOrdersViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.adminProductsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.adminUsersViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.cartViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.checkoutViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.mainViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.orderDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.ordersViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.productDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16);
      this.securityViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 17);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(18).put("com.ecommerce.app.ui.customer.address.AddAddressViewModel", ((Provider) addAddressViewModelProvider)).put("com.ecommerce.app.ui.customer.address.AddressViewModel", ((Provider) addressViewModelProvider)).put("com.ecommerce.app.ui.admin.categories.AdminCategoriesViewModel", ((Provider) adminCategoriesViewModelProvider)).put("com.ecommerce.app.ui.admin.dashboard.AdminDashboardViewModel", ((Provider) adminDashboardViewModelProvider)).put("com.ecommerce.app.ui.admin.orders.AdminOrdersViewModel", ((Provider) adminOrdersViewModelProvider)).put("com.ecommerce.app.ui.admin.products.AdminProductsViewModel", ((Provider) adminProductsViewModelProvider)).put("com.ecommerce.app.ui.admin.users.AdminUsersViewModel", ((Provider) adminUsersViewModelProvider)).put("com.ecommerce.app.ui.auth.AuthViewModel", ((Provider) authViewModelProvider)).put("com.ecommerce.app.ui.customer.cart.CartViewModel", ((Provider) cartViewModelProvider)).put("com.ecommerce.app.ui.customer.cart.CheckoutViewModel", ((Provider) checkoutViewModelProvider)).put("com.ecommerce.app.ui.customer.home.HomeViewModel", ((Provider) homeViewModelProvider)).put("com.ecommerce.app.ui.MainViewModel", ((Provider) mainViewModelProvider)).put("com.ecommerce.app.ui.customer.orders.OrderDetailViewModel", ((Provider) orderDetailViewModelProvider)).put("com.ecommerce.app.ui.customer.orders.OrdersViewModel", ((Provider) ordersViewModelProvider)).put("com.ecommerce.app.ui.customer.products.ProductDetailViewModel", ((Provider) productDetailViewModelProvider)).put("com.ecommerce.app.ui.customer.profile.ProfileViewModel", ((Provider) profileViewModelProvider)).put("com.ecommerce.app.ui.customer.search.SearchViewModel", ((Provider) searchViewModelProvider)).put("com.ecommerce.app.ui.customer.profile.security.SecurityViewModel", ((Provider) securityViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.ecommerce.app.ui.customer.address.AddAddressViewModel 
          return (T) new AddAddressViewModel(viewModelCImpl.addressRepository());

          case 1: // com.ecommerce.app.ui.customer.address.AddressViewModel 
          return (T) new AddressViewModel(viewModelCImpl.addressRepository());

          case 2: // com.ecommerce.app.ui.admin.categories.AdminCategoriesViewModel 
          return (T) new AdminCategoriesViewModel(viewModelCImpl.categoryRepository());

          case 3: // com.ecommerce.app.ui.admin.dashboard.AdminDashboardViewModel 
          return (T) new AdminDashboardViewModel(viewModelCImpl.productRepository(), viewModelCImpl.orderRepository(), viewModelCImpl.userRepository());

          case 4: // com.ecommerce.app.ui.admin.orders.AdminOrdersViewModel 
          return (T) new AdminOrdersViewModel(viewModelCImpl.orderRepository());

          case 5: // com.ecommerce.app.ui.admin.products.AdminProductsViewModel 
          return (T) new AdminProductsViewModel(viewModelCImpl.productRepository());

          case 6: // com.ecommerce.app.ui.admin.users.AdminUsersViewModel 
          return (T) new AdminUsersViewModel(viewModelCImpl.userRepository());

          case 7: // com.ecommerce.app.ui.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.emailRepositoryProvider.get(), singletonCImpl.tokenManagerProvider.get());

          case 8: // com.ecommerce.app.ui.customer.cart.CartViewModel 
          return (T) new CartViewModel(viewModelCImpl.cartRepository());

          case 9: // com.ecommerce.app.ui.customer.cart.CheckoutViewModel 
          return (T) new CheckoutViewModel(viewModelCImpl.orderRepository(), viewModelCImpl.addressRepository());

          case 10: // com.ecommerce.app.ui.customer.home.HomeViewModel 
          return (T) new HomeViewModel(viewModelCImpl.productRepository(), viewModelCImpl.categoryRepository(), viewModelCImpl.userRepository(), viewModelCImpl.cartRepository());

          case 11: // com.ecommerce.app.ui.MainViewModel 
          return (T) new MainViewModel(singletonCImpl.tokenManagerProvider.get());

          case 12: // com.ecommerce.app.ui.customer.orders.OrderDetailViewModel 
          return (T) new OrderDetailViewModel(viewModelCImpl.orderRepository());

          case 13: // com.ecommerce.app.ui.customer.orders.OrdersViewModel 
          return (T) new OrdersViewModel(viewModelCImpl.orderRepository());

          case 14: // com.ecommerce.app.ui.customer.products.ProductDetailViewModel 
          return (T) new ProductDetailViewModel(viewModelCImpl.productRepository(), viewModelCImpl.cartRepository(), viewModelCImpl.userRepository());

          case 15: // com.ecommerce.app.ui.customer.profile.ProfileViewModel 
          return (T) new ProfileViewModel(viewModelCImpl.userRepository(), singletonCImpl.tokenManagerProvider.get());

          case 16: // com.ecommerce.app.ui.customer.search.SearchViewModel 
          return (T) new SearchViewModel(viewModelCImpl.productRepository(), viewModelCImpl.categoryRepository());

          case 17: // com.ecommerce.app.ui.customer.profile.security.SecurityViewModel 
          return (T) new SecurityViewModel(singletonCImpl.emailRepositoryProvider.get(), viewModelCImpl.userRepository());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ECommerceApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ECommerceApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ECommerceApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<TokenManager> tokenManagerProvider;

    private Provider<HttpLoggingInterceptor> provideLoggingInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<AddressApiService> provideAddressApiServiceProvider;

    private Provider<CategoryApiService> provideCategoryApiServiceProvider;

    private Provider<ProductApiService> provideProductApiServiceProvider;

    private Provider<OrderApiService> provideOrderApiServiceProvider;

    private Provider<UserApiService> provideUserApiServiceProvider;

    private Provider<AuthApiService> provideAuthApiServiceProvider;

    private Provider<AuthRepository> authRepositoryProvider;

    private Provider<EmailApiService> provideEmailApiServiceProvider;

    private Provider<EmailRepository> emailRepositoryProvider;

    private Provider<CartApiService> provideCartApiServiceProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private AuthInterceptor authInterceptor() {
      return new AuthInterceptor(tokenManagerProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.tokenManagerProvider = DoubleCheck.provider(new SwitchingProvider<TokenManager>(singletonCImpl, 3));
      this.provideLoggingInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<HttpLoggingInterceptor>(singletonCImpl, 4));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 2));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 1));
      this.provideAddressApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<AddressApiService>(singletonCImpl, 0));
      this.provideCategoryApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<CategoryApiService>(singletonCImpl, 5));
      this.provideProductApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<ProductApiService>(singletonCImpl, 6));
      this.provideOrderApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<OrderApiService>(singletonCImpl, 7));
      this.provideUserApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<UserApiService>(singletonCImpl, 8));
      this.provideAuthApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<AuthApiService>(singletonCImpl, 10));
      this.authRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, 9));
      this.provideEmailApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<EmailApiService>(singletonCImpl, 12));
      this.emailRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<EmailRepository>(singletonCImpl, 11));
      this.provideCartApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<CartApiService>(singletonCImpl, 13));
    }

    @Override
    public void injectECommerceApp(ECommerceApp eCommerceApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.ecommerce.app.data.api.AddressApiService 
          return (T) NetworkModule_ProvideAddressApiServiceFactory.provideAddressApiService(singletonCImpl.provideRetrofitProvider.get());

          case 1: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 2: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.authInterceptor(), singletonCImpl.provideLoggingInterceptorProvider.get());

          case 3: // com.ecommerce.app.util.TokenManager 
          return (T) new TokenManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // okhttp3.logging.HttpLoggingInterceptor 
          return (T) NetworkModule_ProvideLoggingInterceptorFactory.provideLoggingInterceptor();

          case 5: // com.ecommerce.app.data.api.CategoryApiService 
          return (T) NetworkModule_ProvideCategoryApiServiceFactory.provideCategoryApiService(singletonCImpl.provideRetrofitProvider.get());

          case 6: // com.ecommerce.app.data.api.ProductApiService 
          return (T) NetworkModule_ProvideProductApiServiceFactory.provideProductApiService(singletonCImpl.provideRetrofitProvider.get());

          case 7: // com.ecommerce.app.data.api.OrderApiService 
          return (T) NetworkModule_ProvideOrderApiServiceFactory.provideOrderApiService(singletonCImpl.provideRetrofitProvider.get());

          case 8: // com.ecommerce.app.data.api.UserApiService 
          return (T) NetworkModule_ProvideUserApiServiceFactory.provideUserApiService(singletonCImpl.provideRetrofitProvider.get());

          case 9: // com.ecommerce.app.data.repository.AuthRepository 
          return (T) new AuthRepository(singletonCImpl.provideAuthApiServiceProvider.get());

          case 10: // com.ecommerce.app.data.api.AuthApiService 
          return (T) NetworkModule_ProvideAuthApiServiceFactory.provideAuthApiService(singletonCImpl.provideRetrofitProvider.get());

          case 11: // com.ecommerce.app.data.repository.EmailRepository 
          return (T) new EmailRepository(singletonCImpl.provideEmailApiServiceProvider.get());

          case 12: // com.ecommerce.app.data.api.EmailApiService 
          return (T) NetworkModule_ProvideEmailApiServiceFactory.provideEmailApiService(singletonCImpl.provideRetrofitProvider.get());

          case 13: // com.ecommerce.app.data.api.CartApiService 
          return (T) NetworkModule_ProvideCartApiServiceFactory.provideCartApiService(singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
