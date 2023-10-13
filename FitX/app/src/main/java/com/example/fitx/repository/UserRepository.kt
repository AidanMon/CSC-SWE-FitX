package com.example.fitx.repository
import com.example.fitx.ExperienceLevel
import com.example.fitx.SportName
import com.example.fitx.User

class UserRepository {

    fun getUser(): User {
        return User("", "", 0, 0, "", ExperienceLevel.Beginner, SportName.Tennis)
    }

}