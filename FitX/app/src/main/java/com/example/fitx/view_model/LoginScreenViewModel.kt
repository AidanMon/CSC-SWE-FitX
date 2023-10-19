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

/**
 * Login Screen ViewModel binds to the Login Screen Xml to set the null values of user input
 */
class LoginScreenViewModel: ViewModel() {
    private val _userEmail = MutableLiveData<String?>(null)
    val userEmail: LiveData<String?> = _userEmail

    private val _userPassword = MutableLiveData<String?>(null)
    val userPassword: LiveData<String?> = _userPassword
}