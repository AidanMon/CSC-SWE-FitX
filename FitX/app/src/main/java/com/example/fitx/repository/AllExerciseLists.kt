package com.example.fitx.repository

import com.example.fitx.model.Exercise

object AllExerciseLists {
    var exerciseList = mutableListOf<Exercise>()
    var currentCreateWorkout = mutableListOf<Exercise>()
    var userMadeWorkouts = mutableListOf<Pair<String, MutableList<Exercise>>>()
    var currentSelectedWorkout = Pair("", mutableListOf<Exercise>())
}