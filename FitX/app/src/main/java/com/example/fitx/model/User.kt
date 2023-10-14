package com.example.fitx.model

import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName

data class User(
    var firstName: String,

    var lastName: String,

    var age: Number,

    var weight: Number,

    var email:String,

    var experienceLevel: ExperienceLevel,

    var sport: SportName
){




}

