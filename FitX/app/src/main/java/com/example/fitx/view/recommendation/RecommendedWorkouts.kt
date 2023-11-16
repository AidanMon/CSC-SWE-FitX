package com.example.fitx.view.recommendation

import android.content.ContentValues.TAG
import android.util.Log
import com.example.fitx.model.Exercise
import com.example.fitx.model.UserWorkout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecommendedWorkouts {
    private var workout = ArrayList<Exercise>()
    private var workouts = ArrayList<UserWorkout>()
    private var firestore = Firebase.firestore
    fun getWorkout(Type: String, Level: String): ArrayList<UserWorkout> {
        for (i in 1..2) {
            firestore.collection("Workouts/" + Type + "/Exercises")
                .whereEqualTo("Level", Level)
                .whereEqualTo("Workout", i)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        workout.add(
                            Exercise(
                                document.getString("Ename") ?: "",
                                document.getLong("ExerciseID")?.toInt() ?: 0,
                                document.getString("MuscleGroup") ?: "",
                                document.getString("VideoID") ?: ""
                            )
                        )
                    }
                    val sampleWorkout = UserWorkout(Level + " " + Type + " Workout " + i,workout)
                    workouts.add(sampleWorkout)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
        return workouts
    }
}