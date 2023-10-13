package com.example.fitx.view_model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010/\u001a\u00020\u0007J\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u000201J\u000e\u00103\u001a\u0002012\u0006\u00104\u001a\u00020\u0005J\u000e\u00105\u001a\u0002012\u0006\u00106\u001a\u00020\u0007J\u000e\u00107\u001a\u0002012\u0006\u00108\u001a\u00020\tJ\u000e\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020\tJ\u000e\u0010;\u001a\u0002012\u0006\u0010<\u001a\u00020\tJ\u000e\u0010=\u001a\u0002012\u0006\u0010>\u001a\u00020\u0005J\u0006\u0010?\u001a\u000201J\u0006\u0010@\u001a\u000201R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0011R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0019\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0019\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u000e\u0010+\u001a\u00020,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\"\u00a8\u0006A"}, d2 = {"Lcom/example/fitx/view_model/CreateAccountViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_userAge", "Landroidx/lifecycle/MutableLiveData;", "", "_userData", "Lcom/example/fitx/User;", "_userEmail", "", "_userFirstName", "_userLastName", "_userWeight", "levels", "", "Lcom/example/fitx/ExperienceLevel;", "getLevels", "()Ljava/util/List;", "selectedExperienceLevel", "getSelectedExperienceLevel", "()Landroidx/lifecycle/MutableLiveData;", "selectedExperienceLevelPosition", "", "getSelectedExperienceLevelPosition", "selectedSport", "Lcom/example/fitx/SportName;", "getSelectedSport", "selectedSportNamePosition", "getSelectedSportNamePosition", "sports", "getSports", "userAge", "Landroidx/lifecycle/LiveData;", "getUserAge", "()Landroidx/lifecycle/LiveData;", "userData", "getUserData", "userEmail", "getUserEmail", "userFirstName", "getUserFirstName", "userLastName", "getUserLastName", "userRepository", "Lcom/example/fitx/repository/UserRepository;", "userWeight", "getUserWeight", "createUserWithUpdatedData", "getCurrentUserData", "", "getUserDataFromRepository", "setAge", "age", "setUser", "user", "setUserEmail", "email", "setUserFirstName", "firstName", "setUserLastName", "lastName", "setWeight", "weight", "updateSelectedExperienceLevel", "updateSelectedSportName", "app_debug"})
public final class CreateAccountViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.fitx.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.fitx.User> _userData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.fitx.User> userData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _userFirstName = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> userFirstName = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _userLastName = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> userLastName = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _userEmail = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> userEmail = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Number> _userAge = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Number> userAge = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Number> _userWeight = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Number> userWeight = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.fitx.SportName> sports = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.fitx.ExperienceLevel> levels = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.fitx.SportName> selectedSport = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.fitx.ExperienceLevel> selectedExperienceLevel = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> selectedExperienceLevelPosition = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> selectedSportNamePosition = null;
    
    public CreateAccountViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.fitx.User> getUserData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getUserFirstName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getUserLastName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getUserEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Number> getUserAge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Number> getUserWeight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.fitx.SportName> getSports() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.fitx.ExperienceLevel> getLevels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.fitx.SportName> getSelectedSport() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.fitx.ExperienceLevel> getSelectedExperienceLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getSelectedExperienceLevelPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getSelectedSportNamePosition() {
        return null;
    }
    
    public final void getCurrentUserData() {
    }
    
    public final void setUser(@org.jetbrains.annotations.NotNull
    com.example.fitx.User user) {
    }
    
    public final void getUserDataFromRepository() {
    }
    
    public final void setUserFirstName(@org.jetbrains.annotations.NotNull
    java.lang.String firstName) {
    }
    
    public final void setUserLastName(@org.jetbrains.annotations.NotNull
    java.lang.String lastName) {
    }
    
    public final void setUserEmail(@org.jetbrains.annotations.NotNull
    java.lang.String email) {
    }
    
    public final void setAge(@org.jetbrains.annotations.NotNull
    java.lang.Number age) {
    }
    
    public final void setWeight(@org.jetbrains.annotations.NotNull
    java.lang.Number weight) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.fitx.User createUserWithUpdatedData() {
        return null;
    }
    
    public final void updateSelectedSportName() {
    }
    
    public final void updateSelectedExperienceLevel() {
    }
}