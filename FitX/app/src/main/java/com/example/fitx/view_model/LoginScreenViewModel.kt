package com.example.fitx.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitx.model.User
import com.example.fitx.model.UserLogin
import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName
import com.example.fitx.repository.UserRepository

class LoginScreenViewModel: ViewModel() {
    private val userRepository = UserRepository()
    private val _userLoginData = MutableLiveData<UserLogin?>(null)
    private val _userName = MutableLiveData<String?>(null)
    val userName: LiveData<String?> = _userName

    private val _userPassword = MutableLiveData<String?>(null)
    val userPassword: LiveData<String?> = _userPassword

    val userNameString: String = ""
    val passwordString: String = ""

    var authString: String = ""

    fun setUserName(userName: String) {
        _userName.value = userName
        Log.d("TwoWayBinding", "UserName changed to: $userName")
    }

    fun setUserPassword(password: String) {
        _userPassword.value = password
        Log.d("TwoWayBinding", "Password changed to: $password")
    }

    fun getLoginInfo(): Boolean {
        val currentUserData = _userLoginData.value ?: UserLogin("","")

        var updateUserName = currentUserData.username
        var updatePassword = currentUserData.password

        // Use the values from your LiveData properties, if they are not null
        userName.value?.let { updateUserName = it }
        userPassword.value?.let {updatePassword = it}

        Log.d("LoginScreenViewModel","UserName: "+ updateUserName)
        Log.d("LoginScreenViewModel","Password: "+ updatePassword)

        // Create a new User object with the updated data
        val authValue = userRepository.Signin(updateUserName, updatePassword)
        return authValue
    }
    fun changeAuthString(): String{
        return "Authentication Failed Try again"
    }

}