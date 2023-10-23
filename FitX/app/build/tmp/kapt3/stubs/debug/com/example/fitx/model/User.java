package com.example.fitx.model;

/**
 * User is a data class to view the available information that can
 * be received about a user
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0006H\u00c6\u0003J\t\u0010!\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003JO\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f\u00a8\u0006,"}, d2 = {"Lcom/example/fitx/model/User;", "", "firstName", "", "lastName", "age", "", "weight", "email", "experienceLevel", "sportId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Number;)V", "getAge", "()Ljava/lang/Number;", "setAge", "(Ljava/lang/Number;)V", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getExperienceLevel", "setExperienceLevel", "getFirstName", "setFirstName", "getLastName", "setLastName", "getSportId", "setSportId", "getWeight", "setWeight", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class User {
    @org.jetbrains.annotations.NotNull
    private java.lang.String firstName;
    @org.jetbrains.annotations.NotNull
    private java.lang.String lastName;
    @org.jetbrains.annotations.NotNull
    private java.lang.Number age;
    @org.jetbrains.annotations.NotNull
    private java.lang.Number weight;
    @org.jetbrains.annotations.NotNull
    private java.lang.String email;
    @org.jetbrains.annotations.NotNull
    private java.lang.String experienceLevel;
    @org.jetbrains.annotations.NotNull
    private java.lang.Number sportId;
    
    public User(@org.jetbrains.annotations.NotNull
    java.lang.String firstName, @org.jetbrains.annotations.NotNull
    java.lang.String lastName, @org.jetbrains.annotations.NotNull
    java.lang.Number age, @org.jetbrains.annotations.NotNull
    java.lang.Number weight, @org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String experienceLevel, @org.jetbrains.annotations.NotNull
    java.lang.Number sportId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLastName() {
        return null;
    }
    
    public final void setLastName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Number getAge() {
        return null;
    }
    
    public final void setAge(@org.jetbrains.annotations.NotNull
    java.lang.Number p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Number getWeight() {
        return null;
    }
    
    public final void setWeight(@org.jetbrains.annotations.NotNull
    java.lang.Number p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getEmail() {
        return null;
    }
    
    public final void setEmail(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getExperienceLevel() {
        return null;
    }
    
    public final void setExperienceLevel(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Number getSportId() {
        return null;
    }
    
    public final void setSportId(@org.jetbrains.annotations.NotNull
    java.lang.Number p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Number component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Number component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Number component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.fitx.model.User copy(@org.jetbrains.annotations.NotNull
    java.lang.String firstName, @org.jetbrains.annotations.NotNull
    java.lang.String lastName, @org.jetbrains.annotations.NotNull
    java.lang.Number age, @org.jetbrains.annotations.NotNull
    java.lang.Number weight, @org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String experienceLevel, @org.jetbrains.annotations.NotNull
    java.lang.Number sportId) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}