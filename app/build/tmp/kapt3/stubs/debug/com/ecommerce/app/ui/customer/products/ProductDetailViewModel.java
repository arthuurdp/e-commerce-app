package com.ecommerce.app.ui.customer.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.cart.CartItemResponse;
import com.ecommerce.app.data.model.comment.CommentRequest;
import com.ecommerce.app.data.model.comment.CommentResponse;
import com.ecommerce.app.data.model.product.ProductDetailsResponse;
import com.ecommerce.app.data.model.review.ReviewRequest;
import com.ecommerce.app.data.model.review.ReviewResponse;
import com.ecommerce.app.data.repository.CartRepository;
import com.ecommerce.app.data.repository.CommentRepository;
import com.ecommerce.app.data.repository.FavoriteRepository;
import com.ecommerce.app.data.repository.ProductRepository;
import com.ecommerce.app.data.repository.ReviewRepository;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010@\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&2\u0006\u0010B\u001a\u00020$J \u0010C\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010$J\u000e\u0010G\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&J\u0010\u0010H\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&H\u0002J\u0016\u0010I\u001a\u00020\u001c2\u0006\u0010J\u001a\u00020&2\u0006\u0010A\u001a\u00020&J\u000e\u0010K\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&J\u000e\u0010L\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&J\u000e\u0010M\u001a\u00020\u001c2\u0006\u0010N\u001a\u00020&J\u000e\u0010O\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&J\b\u0010P\u001a\u00020\u001cH\u0002J\u0006\u0010Q\u001a\u00020\u001cJ\u0006\u0010R\u001a\u00020\u001cJ\u0006\u0010S\u001a\u00020\u001cJ\u0006\u0010T\u001a\u00020\u001cJ\u0006\u0010U\u001a\u00020\u001cJ\u000e\u0010V\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020&R\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001a0\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001a0\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\'\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001f\u0010+\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u001f\u0010-\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010*R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010*R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u00101\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001a0\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010*R\u001f\u00103\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010*R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u00105\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010*R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u001f0(\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010*R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001a0\u00110(\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010*R\u0019\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0(\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010*R\u0019\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0(\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010*R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006W"}, d2 = {"Lcom/ecommerce/app/ui/customer/products/ProductDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/ecommerce/app/data/repository/ProductRepository;", "cartRepository", "Lcom/ecommerce/app/data/repository/CartRepository;", "userRepository", "Lcom/ecommerce/app/data/repository/UserRepository;", "reviewRepository", "Lcom/ecommerce/app/data/repository/ReviewRepository;", "favoriteRepository", "Lcom/ecommerce/app/data/repository/FavoriteRepository;", "commentRepository", "Lcom/ecommerce/app/data/repository/CommentRepository;", "(Lcom/ecommerce/app/data/repository/ProductRepository;Lcom/ecommerce/app/data/repository/CartRepository;Lcom/ecommerce/app/data/repository/UserRepository;Lcom/ecommerce/app/data/repository/ReviewRepository;Lcom/ecommerce/app/data/repository/FavoriteRepository;Lcom/ecommerce/app/data/repository/CommentRepository;)V", "_addCommentState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/comment/CommentResponse;", "_addReviewState", "Lcom/ecommerce/app/data/model/review/ReviewResponse;", "_addToCartState", "Lcom/ecommerce/app/data/model/cart/CartItemResponse;", "_averageRating", "", "_commentsState", "", "_deleteCommentState", "", "_favoriteState", "_isFavorite", "", "_product", "Lcom/ecommerce/app/data/model/product/ProductDetailsResponse;", "_reviews", "_userEmail", "", "_userId", "", "addCommentState", "Landroidx/lifecycle/LiveData;", "getAddCommentState", "()Landroidx/lifecycle/LiveData;", "addReviewState", "getAddReviewState", "addToCartState", "getAddToCartState", "averageRating", "getAverageRating", "commentsState", "getCommentsState", "deleteCommentState", "getDeleteCommentState", "favoriteState", "getFavoriteState", "isFavorite", "product", "getProduct", "reviews", "getReviews", "userEmail", "getUserEmail", "userId", "getUserId", "addComment", "productId", "content", "addReview", "rating", "", "comment", "addToCart", "checkIfFavorite", "deleteComment", "commentId", "loadAverageRating", "loadComments", "loadProduct", "id", "loadReviews", "loadUserInfo", "resetAddCommentState", "resetAddReviewState", "resetAddToCartState", "resetDeleteCommentState", "resetFavoriteState", "toggleFavorite", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProductDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.ProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.CartRepository cartRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.ReviewRepository reviewRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.FavoriteRepository favoriteRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.CommentRepository commentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> _product = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> product = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartItemResponse>> _addToCartState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartItemResponse>> addToCartState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _userEmail = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> userEmail = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Long> _userId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Long> userId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.util.List<com.ecommerce.app.data.model.review.ReviewResponse>>> _reviews = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.util.List<com.ecommerce.app.data.model.review.ReviewResponse>>> reviews = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.lang.Double>> _averageRating = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.Double>> averageRating = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.review.ReviewResponse>> _addReviewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.review.ReviewResponse>> addReviewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> _favoriteState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> favoriteState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isFavorite = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isFavorite = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<java.util.List<com.ecommerce.app.data.model.comment.CommentResponse>>> _commentsState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.util.List<com.ecommerce.app.data.model.comment.CommentResponse>>> commentsState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.comment.CommentResponse>> _addCommentState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.comment.CommentResponse>> addCommentState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> _deleteCommentState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> deleteCommentState = null;
    
    @javax.inject.Inject()
    public ProductDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.ProductRepository productRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.CartRepository cartRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.ReviewRepository reviewRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.FavoriteRepository favoriteRepository, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.CommentRepository commentRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartItemResponse>> getAddToCartState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getUserEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Long> getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.util.List<com.ecommerce.app.data.model.review.ReviewResponse>>> getReviews() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.lang.Double>> getAverageRating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.review.ReviewResponse>> getAddReviewState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getFavoriteState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isFavorite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<java.util.List<com.ecommerce.app.data.model.comment.CommentResponse>>> getCommentsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.comment.CommentResponse>> getAddCommentState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<kotlin.Unit>> getDeleteCommentState() {
        return null;
    }
    
    public final void loadProduct(long id) {
    }
    
    public final void loadReviews(long productId) {
    }
    
    public final void loadAverageRating(long productId) {
    }
    
    public final void loadComments(long productId) {
    }
    
    public final void addComment(long productId, @org.jetbrains.annotations.NotNull()
    java.lang.String content) {
    }
    
    public final void deleteComment(long commentId, long productId) {
    }
    
    public final void resetAddCommentState() {
    }
    
    public final void resetDeleteCommentState() {
    }
    
    public final void addReview(long productId, int rating, @org.jetbrains.annotations.Nullable()
    java.lang.String comment) {
    }
    
    public final void resetAddReviewState() {
    }
    
    public final void toggleFavorite(long productId) {
    }
    
    private final void checkIfFavorite(long productId) {
    }
    
    public final void resetFavoriteState() {
    }
    
    public final void addToCart(long productId) {
    }
    
    public final void resetAddToCartState() {
    }
    
    private final void loadUserInfo() {
    }
}