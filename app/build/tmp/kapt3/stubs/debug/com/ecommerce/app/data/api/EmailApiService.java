package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.email.*;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ*\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ*\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ*\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ*\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J*\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J*\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016J \u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0018J*\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001a"}, d2 = {"Lcom/ecommerce/app/data/api/EmailApiService;", "", "confirmEmail", "Lretrofit2/Response;", "", "", "request", "Lcom/ecommerce/app/data/model/email/VerifyCodeRequest;", "(Lcom/ecommerce/app/data/model/email/VerifyCodeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmEmailChange", "confirmPasswordChange", "forgotPassword", "Lcom/ecommerce/app/data/model/email/ForgotPasswordRequest;", "(Lcom/ecommerce/app/data/model/email/ForgotPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestEmailChange", "Lcom/ecommerce/app/data/model/email/ChangeEmailRequest;", "(Lcom/ecommerce/app/data/model/email/ChangeEmailRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestPasswordChange", "Lcom/ecommerce/app/data/model/email/ChangePasswordRequest;", "(Lcom/ecommerce/app/data/model/email/ChangePasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "Lcom/ecommerce/app/data/model/email/SetPasswordRequest;", "(Lcom/ecommerce/app/data/model/email/SetPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEmailVerification", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyResetCode", "app_debug"})
public abstract interface EmailApiService {
    
    @retrofit2.http.POST(value = "verify-email/send")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendEmailVerification(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "verify-email/confirm")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmEmail(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "email/change")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object requestEmailChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.ChangeEmailRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "email/confirm")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmEmailChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/change")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object requestPasswordChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.ChangePasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/confirm")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmPasswordChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/forgot")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object forgotPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.ForgotPasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/reset")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyResetCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/set")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.email.SetPasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
}