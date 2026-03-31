package com.ecommerce.app;

import com.ecommerce.app.di.NetworkModule;
import com.ecommerce.app.ui.MainActivity_GeneratedInjector;
import com.ecommerce.app.ui.MainViewModel_HiltModules;
import com.ecommerce.app.ui.admin.categories.AdminCategoriesFragment_GeneratedInjector;
import com.ecommerce.app.ui.admin.categories.AdminCategoriesViewModel_HiltModules;
import com.ecommerce.app.ui.admin.dashboard.AdminDashboardFragment_GeneratedInjector;
import com.ecommerce.app.ui.admin.dashboard.AdminDashboardViewModel_HiltModules;
import com.ecommerce.app.ui.admin.orders.AdminOrdersFragment_GeneratedInjector;
import com.ecommerce.app.ui.admin.orders.AdminOrdersViewModel_HiltModules;
import com.ecommerce.app.ui.admin.products.AdminEditProductFragment_GeneratedInjector;
import com.ecommerce.app.ui.admin.products.AdminProductsFragment_GeneratedInjector;
import com.ecommerce.app.ui.admin.products.AdminProductsViewModel_HiltModules;
import com.ecommerce.app.ui.admin.users.AdminUsersFragment_GeneratedInjector;
import com.ecommerce.app.ui.admin.users.AdminUsersViewModel_HiltModules;
import com.ecommerce.app.ui.auth.AuthViewModel_HiltModules;
import com.ecommerce.app.ui.auth.LoginFragment_GeneratedInjector;
import com.ecommerce.app.ui.auth.RegisterFragment_GeneratedInjector;
import com.ecommerce.app.ui.auth.forgot_password.ForgotPasswordEnterEmailFragment_GeneratedInjector;
import com.ecommerce.app.ui.auth.forgot_password.ForgotPasswordResetPasswordFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.address.AddAddressFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.address.AddAddressViewModel_HiltModules;
import com.ecommerce.app.ui.customer.address.AddressListFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.address.AddressViewModel_HiltModules;
import com.ecommerce.app.ui.customer.cart.CartFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.cart.CartViewModel_HiltModules;
import com.ecommerce.app.ui.customer.cart.CheckoutFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.cart.CheckoutViewModel_HiltModules;
import com.ecommerce.app.ui.customer.home.HomeFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.home.HomeViewModel_HiltModules;
import com.ecommerce.app.ui.customer.orders.OrderDetailFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.orders.OrderDetailViewModel_HiltModules;
import com.ecommerce.app.ui.customer.orders.OrdersFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.orders.OrdersViewModel_HiltModules;
import com.ecommerce.app.ui.customer.products.ProductDetailFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.products.ProductDetailViewModel_HiltModules;
import com.ecommerce.app.ui.customer.profile.EditProfileFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.profile.ProfileFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.profile.ProfileViewModel_HiltModules;
import com.ecommerce.app.ui.customer.profile.security.ChangeEmailEnterNewEmailFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.profile.security.ChangePasswordEnterNewPasswordFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.profile.security.SecurityFragment_GeneratedInjector;
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel_HiltModules;
import com.ecommerce.app.ui.shared.EnterCodeFragment_GeneratedInjector;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_SavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class ECommerceApp_HiltComponents {
  private ECommerceApp_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          ApplicationContextModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          NetworkModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements ECommerceApp_GeneratedInjector,
      FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddAddressViewModel_HiltModules.KeyModule.class,
          AddressViewModel_HiltModules.KeyModule.class,
          AdminCategoriesViewModel_HiltModules.KeyModule.class,
          AdminDashboardViewModel_HiltModules.KeyModule.class,
          AdminOrdersViewModel_HiltModules.KeyModule.class,
          AdminProductsViewModel_HiltModules.KeyModule.class,
          AdminUsersViewModel_HiltModules.KeyModule.class,
          AuthViewModel_HiltModules.KeyModule.class,
          CartViewModel_HiltModules.KeyModule.class,
          CheckoutViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_SavedStateHandleModule.class,
          HomeViewModel_HiltModules.KeyModule.class,
          MainViewModel_HiltModules.KeyModule.class,
          OrderDetailViewModel_HiltModules.KeyModule.class,
          OrdersViewModel_HiltModules.KeyModule.class,
          ProductDetailViewModel_HiltModules.KeyModule.class,
          ProfileViewModel_HiltModules.KeyModule.class,
          SecurityViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddAddressViewModel_HiltModules.BindsModule.class,
          AddressViewModel_HiltModules.BindsModule.class,
          AdminCategoriesViewModel_HiltModules.BindsModule.class,
          AdminDashboardViewModel_HiltModules.BindsModule.class,
          AdminOrdersViewModel_HiltModules.BindsModule.class,
          AdminProductsViewModel_HiltModules.BindsModule.class,
          AdminUsersViewModel_HiltModules.BindsModule.class,
          AuthViewModel_HiltModules.BindsModule.class,
          CartViewModel_HiltModules.BindsModule.class,
          CheckoutViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          HomeViewModel_HiltModules.BindsModule.class,
          MainViewModel_HiltModules.BindsModule.class,
          OrderDetailViewModel_HiltModules.BindsModule.class,
          OrdersViewModel_HiltModules.BindsModule.class,
          ProductDetailViewModel_HiltModules.BindsModule.class,
          ProfileViewModel_HiltModules.BindsModule.class,
          SecurityViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements AdminCategoriesFragment_GeneratedInjector,
      AdminDashboardFragment_GeneratedInjector,
      AdminOrdersFragment_GeneratedInjector,
      AdminEditProductFragment_GeneratedInjector,
      AdminProductsFragment_GeneratedInjector,
      AdminUsersFragment_GeneratedInjector,
      LoginFragment_GeneratedInjector,
      RegisterFragment_GeneratedInjector,
      ForgotPasswordEnterEmailFragment_GeneratedInjector,
      ForgotPasswordResetPasswordFragment_GeneratedInjector,
      AddAddressFragment_GeneratedInjector,
      AddressListFragment_GeneratedInjector,
      CartFragment_GeneratedInjector,
      CheckoutFragment_GeneratedInjector,
      HomeFragment_GeneratedInjector,
      OrderDetailFragment_GeneratedInjector,
      OrdersFragment_GeneratedInjector,
      ProductDetailFragment_GeneratedInjector,
      EditProfileFragment_GeneratedInjector,
      ProfileFragment_GeneratedInjector,
      ChangeEmailEnterNewEmailFragment_GeneratedInjector,
      ChangePasswordEnterNewPasswordFragment_GeneratedInjector,
      SecurityFragment_GeneratedInjector,
      EnterCodeFragment_GeneratedInjector,
      FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
