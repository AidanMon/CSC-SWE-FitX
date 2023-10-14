package com.example.fitx.repository
import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName
import com.example.fitx.model.User

class UserRepository {
    fun getUser(): User {
        return User("", "", 0, 0, "", ExperienceLevel.Beginner, SportName.Tennis)
    }
}