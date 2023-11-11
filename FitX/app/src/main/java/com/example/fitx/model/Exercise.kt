package com.example.fitx.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Exercise(
    var exerciseName: String,

    var exerciseID: Int,

    var muscleGroup: String,

    var videoID: String,

){
    constructor() : this("", 0,"","")

}