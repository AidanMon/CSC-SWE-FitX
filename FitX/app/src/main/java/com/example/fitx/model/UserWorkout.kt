package com.example.fitx.model

data class UserWorkout(
    var workoutName: String,

    var exerciseList: MutableList<Exercise>

)
