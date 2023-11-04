package com.example.fitx.repository

import com.example.fitx.model.Exercise
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class ExerciseRepository {
    private lateinit var firestore: FirebaseFirestore


    interface ExerciseCallback {
        fun onExercisesReceived(exerciseList: MutableList<Exercise>)
        fun onError(exception: MutableList<Exercise>)//Differ
    }


    fun getAllExercises(callback: ExerciseCallback){
        var exerciseList = mutableListOf<Exercise>()

        firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Exercises")
        collectionRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot = task.result
                    if (querySnapshot != null) {
                        for(document in querySnapshot){
                            exerciseList.add(Exercise(document.getString("Ename")?: "",
                                document.getLong("ExerciseID")?.toInt() ?: 0,
                                document.getString("MuscleGroup")?: "",
                                document.getString("VideoID")?: ""))
                        }
                        callback.onExercisesReceived(exerciseList)
                    }
                } else {
                    // Handle errors if the query was not successful
                    val exception = task.exception
                    if (exception != null) {
                        for (i in 0 until 6){
                            val testE = Exercise("Damn", 5,"l","h")
                            exerciseList.add(testE)
                        }
                        callback.onError(exerciseList)
                    }
                }
            }
    }
}