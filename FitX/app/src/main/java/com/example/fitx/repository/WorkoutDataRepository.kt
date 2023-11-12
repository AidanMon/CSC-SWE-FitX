package com.example.fitx.repository

import android.widget.TextView
import com.example.fitx.model.InputData
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import java.util.Calendar



class WorkoutDataRepository {

    interface InputDataCallback {
        fun onDataInputted()
        fun onError(errorMessage: String)
    }


    fun inputWorkoutData(callback: InputDataCallback, repCountIntList: MutableList<MutableList<Int>>, weightIntList: MutableList<MutableList<Int>>){

        val currentUser = FirebaseAuth.getInstance().currentUser

        // Get the current date and time
        val currentDateTime = Calendar.getInstance().time
        // Convert the Date to a Timestamp
        val timestamp = Timestamp(currentDateTime)

        //Code to save the Workout
        if (currentUser != null) {
            //Get a reference to the Firestore database
            val db = FirebaseFirestore.getInstance()
            //Get a reference to the user's document
            val userDocumentReference = db.collection("users").document(currentUser.uid)

            var inputData: MutableList<InputData> = mutableListOf()

            //Structuring the input data
            for(i in 0 until repCountIntList.size){
                var theData = InputData()
                theData.exerciseID = AllExerciseLists.currentSelectedWorkout.second[i].exerciseID
                theData.exerciseName = AllExerciseLists.currentSelectedWorkout.second[i].exerciseName
                theData.muscleGroup = AllExerciseLists.currentSelectedWorkout.second[i].muscleGroup
                theData.date = timestamp

                for(setIndex in 0 until repCountIntList[i].size){
                    val temp = setIndex+1
                    val setPair = Pair( "Set$temp", Pair(repCountIntList[i][setIndex],weightIntList[i][setIndex]))
                    theData.setList.add(setPair)
                }
                val testData = InputData()
                if(theData != testData){
                    inputData.add(theData)
                }
            }

            //Creating the hash maps
            var documentList = mutableListOf<MutableMap<String, Any>>()
            for(data in inputData){ //For each exercise
                var myDocument = mutableMapOf<String, Any>("exerciseID" to data.exerciseID, "exerciseName" to data.exerciseName, "muscleGroup" to data.muscleGroup, "date" to data.date)

                for(set in data.setList){   //For each set
                    var setDataHashMap = mutableMapOf("Count" to set.second.first, "Weight" to set.second.second)
                    if(set.second.first != 0 && set.second.second != 0){    //If count or weight is zero, do not add the set
                        myDocument[set.first] = setDataHashMap
                    }
                }

                //Checking if any sets were added
                val fieldsWithSetPrefix = myDocument.filterKeys { it.startsWith("Set") }
                if (fieldsWithSetPrefix.isNotEmpty()) { //There is at least one set added
                    documentList.add(myDocument)
                }
            }

            //Adding the documents
            for(document in documentList){
                //Add all the input data with for loop
                userDocumentReference.collection("Input Data")
                    .add(document)
                    .addOnFailureListener {
                        // Handle errors
                        callback.onError("Error while adding data to the database")
                    }
            }
            callback.onDataInputted()
        }//User could not be authenticated
        else{
            callback.onError("User Could not be authenticated")
        }
    }
}