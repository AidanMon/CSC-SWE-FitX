package com.example.fitx.repository
import android.annotation.SuppressLint
import android.util.Log
import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName
import com.example.fitx.model.User
import com.example.fitx.model.UserLogin

class UserRepository {
    fun getUser(): User {
        return User("", "", 0, 0, "", ExperienceLevel.Beginner, SportName.Tennis)
    }

    fun UserLoginInfo(): UserLogin {
        return UserLogin("AriKey1234", "1234")
    }

    fun Signin(username: String, password: String): Boolean {
        val currentUser = UserLoginInfo()
        if (username != currentUser.username || password != currentUser.password) {
            return false
        }
        return true
    }
    fun SignUp(){

    }
    fun SignOut(){

    }
}