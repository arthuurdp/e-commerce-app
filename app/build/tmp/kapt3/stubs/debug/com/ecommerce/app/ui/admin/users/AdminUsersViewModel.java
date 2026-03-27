package com.ecommerce.app.ui.admin.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ecommerce.app.data.model.user.UserResponse;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.data.repository.UserRepository;
import com.ecommerce.app.util.NetworkResult;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/ecommerce/app/ui/admin/users/AdminUsersViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepository", "Lcom/ecommerce/app/data/repository/UserRepository;", "(Lcom/ecommerce/app/data/repository/UserRepository;)V", "_usersState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/user/UserResponse;", "usersState", "Landroidx/lifecycle/LiveData;", "getUsersState", "()Landroidx/lifecycle/LiveData;", "loadUsers", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AdminUsersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.user.UserResponse>>> _usersState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.user.UserResponse>>> usersState = null;
    
    @javax.inject.Inject()
    public AdminUsersViewModel(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.user.UserResponse>>> getUsersState() {
        return null;
    }
    
    public final void loadUsers() {
    }
}