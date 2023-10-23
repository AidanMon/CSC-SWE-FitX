package com.example.fitx.databinding;
import com.example.fitx.R;
import com.example.fitx.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CreateAccountBindingImpl extends CreateAccountBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.userPassword, 8);
        sViewsWithIds.put(R.id.signupbutton, 9);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CreateAccountBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private CreateAccountBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5
            , (android.widget.Spinner) bindings[7]
            , (android.widget.Button) bindings[9]
            , (android.widget.Spinner) bindings[6]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[8]
            , (android.widget.EditText) bindings[5]
            );
        this.experiencelevellist.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.sportlist.setTag(null);
        this.userAge.setTag(null);
        this.userEmail.setTag(null);
        this.userFirstName.setTag(null);
        this.userLastName.setTag(null);
        this.userWeight.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
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
        if (BR.user == variableId) {
            setUser((com.example.fitx.view_model.CreateAccountViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable com.example.fitx.view_model.CreateAccountViewModel User) {
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUserUserAge((androidx.lifecycle.LiveData<java.lang.Number>) object, fieldId);
            case 1 :
                return onChangeUserUserEmail((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeUserUserWeight((androidx.lifecycle.LiveData<java.lang.Number>) object, fieldId);
            case 3 :
                return onChangeUserUserFirstName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeUserUserLastName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUserUserAge(androidx.lifecycle.LiveData<java.lang.Number> UserUserAge, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserUserEmail(androidx.lifecycle.LiveData<java.lang.String> UserUserEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserUserWeight(androidx.lifecycle.LiveData<java.lang.Number> UserUserWeight, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserUserFirstName(androidx.lifecycle.LiveData<java.lang.String> UserUserFirstName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserUserLastName(androidx.lifecycle.LiveData<java.lang.String> UserUserLastName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
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
        androidx.lifecycle.LiveData<java.lang.Number> userUserAge = null;
        java.lang.String userUserAgeToString = null;
        androidx.lifecycle.LiveData<java.lang.String> userUserEmail = null;
        java.lang.Number userUserWeightGetValue = null;
        java.lang.String userUserLastNameGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Number> userUserWeight = null;
        java.lang.String userUserWeightToString = null;
        java.lang.String userUserEmailGetValue = null;
        com.example.fitx.view_model.CreateAccountViewModel user = mUser;
        java.lang.String userUserFirstNameGetValue = null;
        java.lang.Number userUserAgeGetValue = null;
        java.util.List<com.example.fitx.model.enums.ExperienceLevel> userLevels = null;
        androidx.lifecycle.LiveData<java.lang.String> userUserFirstName = null;
        androidx.lifecycle.LiveData<java.lang.String> userUserLastName = null;
        java.util.List<com.example.fitx.model.enums.SportName> userSports = null;

        if ((dirtyFlags & 0x7fL) != 0) {


            if ((dirtyFlags & 0x61L) != 0) {

                    if (user != null) {
                        // read user.userAge
                        userUserAge = user.getUserAge();
                    }
                    updateLiveDataRegistration(0, userUserAge);


                    if (userUserAge != null) {
                        // read user.userAge.getValue()
                        userUserAgeGetValue = userUserAge.getValue();
                    }


                    if (userUserAgeGetValue != null) {
                        // read user.userAge.getValue().toString()
                        userUserAgeToString = userUserAgeGetValue.toString();
                    }
            }
            if ((dirtyFlags & 0x62L) != 0) {

                    if (user != null) {
                        // read user.userEmail
                        userUserEmail = user.getUserEmail();
                    }
                    updateLiveDataRegistration(1, userUserEmail);


                    if (userUserEmail != null) {
                        // read user.userEmail.getValue()
                        userUserEmailGetValue = userUserEmail.getValue();
                    }
            }
            if ((dirtyFlags & 0x64L) != 0) {

                    if (user != null) {
                        // read user.userWeight
                        userUserWeight = user.getUserWeight();
                    }
                    updateLiveDataRegistration(2, userUserWeight);


                    if (userUserWeight != null) {
                        // read user.userWeight.getValue()
                        userUserWeightGetValue = userUserWeight.getValue();
                    }


                    if (userUserWeightGetValue != null) {
                        // read user.userWeight.getValue().toString()
                        userUserWeightToString = userUserWeightGetValue.toString();
                    }
            }
            if ((dirtyFlags & 0x60L) != 0) {

                    if (user != null) {
                        // read user.levels
                        userLevels = user.getLevels();
                        // read user.sports
                        userSports = user.getSports();
                    }
            }
            if ((dirtyFlags & 0x68L) != 0) {

                    if (user != null) {
                        // read user.userFirstName
                        userUserFirstName = user.getUserFirstName();
                    }
                    updateLiveDataRegistration(3, userUserFirstName);


                    if (userUserFirstName != null) {
                        // read user.userFirstName.getValue()
                        userUserFirstNameGetValue = userUserFirstName.getValue();
                    }
            }
            if ((dirtyFlags & 0x70L) != 0) {

                    if (user != null) {
                        // read user.userLastName
                        userUserLastName = user.getUserLastName();
                    }
                    updateLiveDataRegistration(4, userUserLastName);


                    if (userUserLastName != null) {
                        // read user.userLastName.getValue()
                        userUserLastNameGetValue = userUserLastName.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x60L) != 0) {
            // api target 1

            androidx.databinding.adapters.AbsSpinnerBindingAdapter.setEntries(this.experiencelevellist, userLevels);
            androidx.databinding.adapters.AbsSpinnerBindingAdapter.setEntries(this.sportlist, userSports);
        }
        if ((dirtyFlags & 0x61L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userAge, userUserAgeToString);
        }
        if ((dirtyFlags & 0x62L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userEmail, userUserEmailGetValue);
        }
        if ((dirtyFlags & 0x68L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userFirstName, userUserFirstNameGetValue);
        }
        if ((dirtyFlags & 0x70L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userLastName, userUserLastNameGetValue);
        }
        if ((dirtyFlags & 0x64L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userWeight, userUserWeightToString);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user.userAge
        flag 1 (0x2L): user.userEmail
        flag 2 (0x3L): user.userWeight
        flag 3 (0x4L): user.userFirstName
        flag 4 (0x5L): user.userLastName
        flag 5 (0x6L): user
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}