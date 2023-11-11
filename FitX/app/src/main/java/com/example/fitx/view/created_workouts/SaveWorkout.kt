package com.example.fitx.view.created_workouts

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.SaveWorkoutFragBinding
import com.example.fitx.repository.AllExerciseLists
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.Timestamp
import java.util.Calendar


class SaveWorkout : Fragment() {
    //TODO(Fix error that removes constraints when going to current created workout fragment)

    private var _binding: SaveWorkoutFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun saveWorkout(view: View){
        val currentUser = FirebaseAuth.getInstance().currentUser

        //Code to save the Workout
        val workoutName = view.findViewById<EditText>(R.id.workoutName)

        if (currentUser != null) {
            //Get a reference to the Firestore database
            val db = FirebaseFirestore.getInstance()

            //Get a reference to the user's document
            val userDocumentReference = db.collection("users").document(currentUser.uid)

            //Creating a new collection with inputted name
            val userCollections = userDocumentReference.collection(workoutName.text.toString())
            if(workoutName.text.toString() == "Scheduled Workouts" || workoutName.text.toString() == "Input Data" || workoutName.text.toString() == "Feedback"){
                //Invalid workout name
                val textName = workoutName.text.toString()
                Toast.makeText(requireActivity(), "Error, you cannot have a workout names $textName", Toast.LENGTH_LONG).show()
            }
            else{
                //Checking if the user workout already exists
                userDocumentReference.collection(workoutName.text.toString())
                    .limit(1) // Limit the query to only retrieve one document
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (querySnapshot.isEmpty) {
                            //Collection does not exist or is empty
                            //Adding the exercises to the user workout
                            for(exercise in AllExerciseLists.currentCreateWorkout){
                                val exerciseName = exercise.exerciseName.replace(" ", "")   //Formatting the exercise name to name the document
                                // Add the data to the collection
                                userCollections.document(exerciseName)
                                    .set(exercise, SetOptions.merge())
                                    .addOnSuccessListener {
                                        // Data added successfully
                                        Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
                                    }
                                    .addOnFailureListener {
                                        // Handle failures
                                        Toast.makeText(requireActivity(), "Error, please try again", Toast.LENGTH_LONG).show()
                                    }
                            }

                            //Empty current workout list and return to home
                            AllExerciseLists.currentCreateWorkout = mutableListOf()
                            findNavController().navigate(R.id.action_SaveWorkout_to_HomePage)

                        } else {
                            //Workout with same name already exists
                            val textName = workoutName.text.toString()
                            Toast.makeText(requireActivity(), "Error, you already have a workout named $textName", Toast.LENGTH_LONG).show()
                        }
                    }
                    .addOnFailureListener {
                        // Handle failures
                        Toast.makeText(requireActivity(), "Error, please try again", Toast.LENGTH_LONG).show()
                    }
                //Adding the new collection to our collection list
                userDocumentReference.get()
                    .addOnSuccessListener { documentSnapshot ->
                        if(documentSnapshot.exists()){
                            if (documentSnapshot.contains("Collections")) {
                                // The field exists in the document
                                val currentValue = documentSnapshot.getString("Collections")
                                var newValue: String
                                newValue = if(currentValue == ""){
                                    workoutName.text.toString()
                                } else{
                                    currentValue + ", " + workoutName.text.toString()
                                }

                                val newCollections = hashMapOf<String, Any>(
                                    "Collections" to newValue
                                )
                                userDocumentReference.update(newCollections)
                            } else {
                                // The field does not exist in the document
                                val updateData = hashMapOf<String, Any>(
                                    "Collections" to workoutName.text.toString()
                                )
                                userDocumentReference.set(updateData, SetOptions.merge())
                            }
                        }
                    }
            }
        } else {
            // Handle the case where the user is not authenticated
            Toast.makeText(requireActivity(), "Error, User could not be authenticated", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SaveWorkoutFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.save_workout_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        //Code to list all exercises in the current workout
        val exerciseListTextView = rootView.findViewById<TextView>(R.id.listOfExercises)
        var exerciseText = AllExerciseLists.currentCreateWorkout[0].exerciseName
        for(i in 1 until AllExerciseLists.currentCreateWorkout.size){
            exerciseText += ", " + AllExerciseLists.currentCreateWorkout[i].exerciseName
        }
        exerciseListTextView.text = exerciseText

        // Return the rootView of the fragment
        return rootView
    }

    //TODO(Update Min API)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)

        val saveWorkoutButton = view.findViewById<Button>(R.id.saveWorkoutButton)
        val workoutName = view.findViewById<EditText>(R.id.workoutName)

        saveWorkoutButton.setOnClickListener{
            // Get the selected date from the DatePicker
            val selectedYear = datePicker.year
            val selectedMonth = datePicker.month
            val selectedDayOfMonth = datePicker.dayOfMonth
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute

            // Get the current date
            val currentDate = Calendar.getInstance()
            val currentYear = currentDate.get(Calendar.YEAR)
            val currentMonth = currentDate.get(Calendar.MONTH)
            val currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

            //Checks if the a workout name has been added
            if(workoutName.text.toString().trim().isEmpty()){
                Toast.makeText(requireActivity(), "Enter a workout name", Toast.LENGTH_LONG).show()
            }//Checks if the workout has been scheduled
            else if (selectedYear == currentYear && selectedMonth == currentMonth && selectedDayOfMonth == currentDayOfMonth) {
                saveWorkout(view)   //Function to save the workout to the user without a schedule
            }
            else{//If it has been scheduled, save that info
                saveWorkout(view)   //Function to save the workout to the user
                val currentUser = FirebaseAuth.getInstance().currentUser
                //Code to save the Workout
                val workoutName = view.findViewById<EditText>(R.id.workoutName)
                if (currentUser != null) {
                    //Get a reference to the Firestore database
                    val db = FirebaseFirestore.getInstance()
                    //Get a reference to the user's document
                    val userDocumentReference = db.collection("users").document(currentUser.uid)

                    // Create a Calendar instance and set the selected date and time
                    val calendar = Calendar.getInstance()
                    calendar.set(selectedYear, selectedMonth, selectedDayOfMonth, selectedHour, selectedMinute)
                    val selectedDate = calendar.time    // Convert the Calendar instance to a Date
                    val timestamp = Timestamp(selectedDate) // Convert the Date to a Timestamp

                    //Saving the schedule
                    userDocumentReference.collection("Scheduled Workouts")
                        .document(workoutName.text.toString())
                        .set(mapOf("timestampField" to timestamp))

                } else {
                    // Handle the case where the user is not authenticated
                    Toast.makeText(requireActivity(), "Error, User could not be authenticated", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}