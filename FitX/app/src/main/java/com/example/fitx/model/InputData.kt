package com.example.fitx.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class InputData(
    var exerciseName: String,

    var exerciseID: Int,

    var muscleGroup: String,  //TODO(Figure out if this is needed

    var setList: MutableList<Pair<String, Pair<Int,Int>>>, //List of (SetName, (repCount, weight))

    var date: Timestamp
){
    constructor() : this("", 0,"", mutableListOf(), Timestamp.now())

}
