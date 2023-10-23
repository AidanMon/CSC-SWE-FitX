package com.example.fitx.model

import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName

/**
 * User is a data class to view the available information that can
 * be received about a user
 */
data class User(
    var firstName: String,

    var lastName: String,

    var age: Number,

    var weight: Number,

    var email: String,

    var experienceLevel: String,

    var sportId: Number
){




}

