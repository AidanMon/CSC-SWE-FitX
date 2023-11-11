package com.example.fitx.repository

import com.example.fitx.model.Exercise
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class ExerciseRepository {
    private lateinit var firestore: FirebaseFirestore


    interface ExerciseCallback {
        fun onExercisesReceived(exerciseList: MutableList<Exercise>)
        fun onError(exception: MutableList<Exercise>)//Differ
    }

    interface UserWorkoutsCallback {
        fun onWorkoutsReceived(exerciseTuples: MutableList<Pair<String, MutableList<Exercise>>>)

        fun onError(exception: MutableList<Pair<String, MutableList<Exercise>>>)//Differ
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
                            val error = Exercise("", 0,"l","h")
                            exerciseList.add(error)
                        }
                        callback.onError(exerciseList)
                    }
                }
            }
    }

    fun getUserWorkouts(callback: UserWorkoutsCallback){
        val userWorkoutList: MutableList<Pair<String, MutableList<Exercise>>> = mutableListOf()
        val currentUser = FirebaseAuth.getInstance().currentUser

        //Get a reference to the user's document
        if(currentUser != null) {
            val db = FirebaseFirestore.getInstance()
            val userDocumentReference = db.collection("users").document(currentUser.uid)

            //Checking for the Collections field
            userDocumentReference.get()
                .addOnSuccessListener { documentSnapshot ->
                    if(documentSnapshot.exists()){
                        if (documentSnapshot.contains("Collections")) {
                            //The field exists in the document
                            val collectionNames = documentSnapshot.getString("Collections").toString().replace(", ", ",").split(",").toMutableList()
                            collectionNames.remove("Scheduled Workouts")
                            collectionNames.remove("Input Data")
                            for (collectionName in collectionNames) {
                                val collectionReference = db.collection("users").document(currentUser.uid).collection(collectionName)

                                collectionReference.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        val exercises = mutableListOf<Exercise>()

                                        for (document in querySnapshot.documents) {
                                            val exercise = document.toObject(Exercise::class.java)
                                            if (exercise != null) {
                                                exercises.add(exercise)
                                            }
                                        }

                                        val exerciseTuple: Pair<String, MutableList<Exercise>> = Pair(collectionName, exercises)
                                        userWorkoutList.add(exerciseTuple)

                                        if (userWorkoutList.size == collectionNames.size) {
                                            // If all collections have been processed, invoke the callback
                                            callback.onWorkoutsReceived(userWorkoutList)
                                        }
                                    }   //Fail to get collection for some reason
                                    .addOnFailureListener {
                                        val error = mutableListOf<Pair<String, MutableList<Exercise>>>()
                                        callback.onError(error)
                                    }
                            }

                        } else {
                            //The field doesn't exist so we return an empty list
                            val error = mutableListOf<Pair<String, MutableList<Exercise>>>()
                            callback.onError(error)
                        }
                    }
                }
        }
    }
}