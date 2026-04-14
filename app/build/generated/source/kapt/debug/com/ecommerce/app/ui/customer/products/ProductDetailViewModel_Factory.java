package com.ecommerce.app.ui.customer.products;

import com.ecommerce.app.data.repository.CartRepository;
import com.ecommerce.app.data.repository.CommentRepository;
import com.ecommerce.app.data.repository.FavoriteRepository;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.data.repository.ReviewRepository;
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
public final class ProductDetailViewModel_Factory implements Factory<ProductDetailViewModel> {
  private final Provider<ProductRepository> productRepositoryProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<ReviewRepository> reviewRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<CommentRepository> commentRepositoryProvider;

  public ProductDetailViewModel_Factory(Provider<ProductRepository> productRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<ReviewRepository> reviewRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<CommentRepository> commentRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.reviewRepositoryProvider = reviewRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.commentRepositoryProvider = commentRepositoryProvider;
  }

  @Override
  public ProductDetailViewModel get() {
    return newInstance(productRepositoryProvider.get(), cartRepositoryProvider.get(), userRepositoryProvider.get(), reviewRepositoryProvider.get(), favoriteRepositoryProvider.get(), commentRepositoryProvider.get());
  }

  public static ProductDetailViewModel_Factory create(
      Provider<ProductRepository> productRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<ReviewRepository> reviewRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<CommentRepository> commentRepositoryProvider) {
    return new ProductDetailViewModel_Factory(productRepositoryProvider, cartRepositoryProvider, userRepositoryProvider, reviewRepositoryProvider, favoriteRepositoryProvider, commentRepositoryProvider);
  }

  public static ProductDetailViewModel newInstance(ProductRepository productRepository,
      CartRepository cartRepository, UserRepository userRepository,
      ReviewRepository reviewRepository, FavoriteRepository favoriteRepository,
      CommentRepository commentRepository) {
    return new ProductDetailViewModel(productRepository, cartRepository, userRepository, reviewRepository, favoriteRepository, commentRepository);
  }
}
