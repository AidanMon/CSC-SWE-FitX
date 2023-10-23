package com.example.fitx.databinding;
import com.example.fitx.R;
import com.example.fitx.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LoginScreenBindingImpl extends LoginScreenBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView, 3);
        sViewsWithIds.put(R.id.Button_login, 4);
        sViewsWithIds.put(R.id.createAccountTextView, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LoginScreenBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private LoginScreenBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[4]
            , (android.widget.Button) bindings[5]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[2]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.userName.setTag(null);
        this.userPassword.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.login == variableId) {
            setLogin((com.example.fitx.view_model.LoginScreenViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLogin(@Nullable com.example.fitx.view_model.LoginScreenViewModel Login) {
        this.mLogin = Login;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.login);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLoginUserEmail((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeLoginUserPassword((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLoginUserEmail(androidx.lifecycle.LiveData<java.lang.String> LoginUserEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeLoginUserPassword(androidx.lifecycle.LiveData<java.lang.String> LoginUserPassword, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        androidx.lifecycle.LiveData<java.lang.String> loginUserEmail = null;
        androidx.lifecycle.LiveData<java.lang.String> loginUserPassword = null;
        java.lang.String loginUserPasswordGetValue = null;
        com.example.fitx.view_model.LoginScreenViewModel login = mLogin;
        java.lang.String loginUserEmailGetValue = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (login != null) {
                        // read login.userEmail
                        loginUserEmail = login.getUserEmail();
                    }
                    updateLiveDataRegistration(0, loginUserEmail);


                    if (loginUserEmail != null) {
                        // read login.userEmail.getValue()
                        loginUserEmailGetValue = loginUserEmail.getValue();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (login != null) {
                        // read login.userPassword
                        loginUserPassword = login.getUserPassword();
                    }
                    updateLiveDataRegistration(1, loginUserPassword);


                    if (loginUserPassword != null) {
                        // read login.userPassword.getValue()
                        loginUserPasswordGetValue = loginUserPassword.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userName, loginUserEmailGetValue);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userPassword, loginUserPasswordGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): login.userEmail
        flag 1 (0x2L): login.userPassword
        flag 2 (0x3L): login
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}