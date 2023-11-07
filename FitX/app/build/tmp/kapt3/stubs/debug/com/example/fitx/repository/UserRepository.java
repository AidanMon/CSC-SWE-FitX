package com.example.fitx.repository;

/**
 * User Repository hold all operations that will be done by the user or retrieving user information
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJZ\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\n0\u0017J*\u0010\u0019\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\n0\u0017J\u001a\u0010\u001a\u001a\u00020\n2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n0\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/fitx/repository/UserRepository;", "", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "messageService", "Lcom/example/fitx/repository/MessageService;", "SignOut", "", "SignUp", "email", "", "password", "fname", "lname", "age", "", "weight", "sport", "experienceLevel", "callback", "Lkotlin/Function1;", "", "Signin", "getCurrentUser", "Lcom/example/fitx/model/User;", "app_debug"})
public final class UserRepository {
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.google.firebase.firestore.FirebaseFirestore firestore;
    @org.jetbrains.annotations.NotNull
    private com.example.fitx.repository.MessageService messageService;
    
    public UserRepository() {
        super();
    }
    
    /**
     * Gets the users full name from the firebase users collection
     */
    public final void getCurrentUser(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.fitx.model.User, kotlin.Unit> callback) {
    }
    
    /**
     * When user logs into the application, it will authenticate using firebase to see if there account exist
     */
    public final void Signin(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback) {
    }
    
    /**
     * When user is registering on the app, it will send there info to the users table in firebase
     */
    public final void SignUp(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    java.lang.String fname, @org.jetbrains.annotations.NotNull
    java.lang.String lname, @org.jetbrains.annotations.NotNull
    java.lang.Number age, @org.jetbrains.annotations.NotNull
    java.lang.Number weight, @org.jetbrains.annotations.NotNull
    java.lang.String sport, @org.jetbrains.annotations.NotNull
    java.lang.String experienceLevel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback) {
    }
    
    /**
     * When user presses the Logout button it will sign them out of the app
     */
    public final void SignOut() {
    }
}