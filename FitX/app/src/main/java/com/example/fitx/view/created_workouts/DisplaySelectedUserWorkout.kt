package com.example.fitx.view.created_workouts

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.DisplaySelectedUserWorkoutFragBinding
import com.example.fitx.model.Exercise
import com.example.fitx.repository.AllExerciseLists
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.Calendar

class DisplaySelectedUserWorkout : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: DisplaySelectedUserWorkoutFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Creating my different exercise lists as well as their button and text view lists
    private var exerciseList = mutableListOf<Exercise>()
    private var textViewList = mutableListOf<TextView>()
    private var buttonList = mutableListOf<Button>()

    //Function to truncate Exercise names that are too long
    private fun setStringMaxLength(input: String, maxLength: Int): String {
        return if (input.length <= maxLength) {
            input
        } else {
            input.substring(0, maxLength) + "."
        }
    }

    val dpTextHeightValue = 64
    val textHeightValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpTextHeightValue.toFloat(), Resources.getSystem().displayMetrics
    )
    val dpTextWidthValue = 380
    val textWidthValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpTextWidthValue.toFloat(), Resources.getSystem().displayMetrics
    )
    //Button
    val dpButtonHeightValue = 40
    val buttonHeightValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpButtonHeightValue.toFloat(), Resources.getSystem().displayMetrics
    )
    val dpButtonWidthValue = 80
    val buttonWidthValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpButtonWidthValue.toFloat(), Resources.getSystem().displayMetrics
    )

    //Function to create our views for each muscle group
    private fun createAllViews(exerciseList: MutableList<Exercise>, parentLayout: ConstraintLayout, textViewList: MutableList<TextView>,
                               buttonList: MutableList<Button>){
        //Creating the TextViews and all Buttons for the leg exercises
        for(exercise in exerciseList){
            //Creating out TextViews
            val newTextView = TextView(requireContext())
            parentLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = setStringMaxLength(exercise.exerciseName,16)// Change the text
            newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
            newTextView.setPadding(11,21,0,0)
            newTextView.height = textHeightValue.toInt()
            newTextView.width = textWidthValue.toInt()
            newTextView.setTextColor(Color.BLACK)
            //newTextView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
            newTextView.setBackgroundResource(R.drawable.exercise_border)
            textViewList.add(newTextView)

            //Creating our buttons
            val newButton = Button(requireContext())
            parentLayout.addView(newButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            newButton.id = View.generateViewId()
            newButton.text = "Example"                                  // Change the text
            newButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            newButton.gravity = Gravity.CENTER                          //Text centered
            newButton.height = buttonHeightValue.toInt()                //Text height
            newButton.width = buttonWidthValue.toInt()                  //Text width
            newButton.setTextColor(Color.WHITE)                         //Text color
            newButton.setBackgroundResource(R.drawable.rounded_button)
            buttonList.add(newButton)
        }
    }

    //Function set the constraints of our added items
    private fun setConstraint(parentLayout: ConstraintLayout, parentID: Int, textViewList: MutableList<TextView>,
                              buttonList: MutableList<Button>){
        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(parentLayout)

        //Constraint for first TextView
        constraintSet.connect(textViewList[0].id, ConstraintSet.TOP, R.id.enterFullScreenButton, ConstraintSet.BOTTOM, 21)
        constraintSet.connect(textViewList[0].id, ConstraintSet.LEFT, parentID,ConstraintSet.LEFT)

        //Constraint for first Button
        constraintSet.connect(buttonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(buttonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 6)

        //Constraints for the remainder of the exercises
        for(i in 1 until textViewList.size){
            //TextView constraints
            constraintSet.connect(textViewList[i].id, ConstraintSet.TOP, textViewList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[i].id, ConstraintSet.LEFT, parentID,ConstraintSet.LEFT)

            //Button constraints
            constraintSet.connect(buttonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(buttonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 6)
        }

        constraintSet.applyTo(parentLayout)
    }

    //Function to add all of the button listeners
    private fun addButtonListeners(exerciseList: MutableList<Exercise>, buttonList: MutableList<Button>, ){
        //Adding the button listeners for the exercise videos
        for (i in 0 until exerciseList.size){
            buttonList[i].setOnClickListener {
                changeVideo(exerciseList[i].videoID, youTubePlayerView)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DisplaySelectedUserWorkoutFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.display_selected_user_workout_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        val header = parentLayout.findViewById<TextView>(R.id.displaySelectedUserWorkoutHeader)
        header.text = AllExerciseLists.currentSelectedWorkout.first

        createAllViews(AllExerciseLists.currentSelectedWorkout.second, parentLayout,textViewList,buttonList)
        setConstraint(parentLayout,R.id.parentLayout,textViewList,buttonList)

        // Return the rootView of the fragment
        return rootView
    }

    //Function to change video
    private fun changeVideo(videoId: String, player: YouTubePlayerView){
        player.visibility = View.VISIBLE
        youTubePlayer?.loadVideo(videoId, 0F)
    }

    @RequiresApi(Build.VERSION_CODES.M) //TODO(Change min API?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initializing our youtube video player
        youTubePlayerView = view.findViewById(R.id.theVideoPlayer)
        lifecycle.addObserver(youTubePlayerView)

        //Fullscreen code
        val fullscreenViewContainer = view.findViewById<FrameLayout>(R.id.full_screen_view_container)
        youTubePlayerView.enableAutomaticInitialization = false
        youTubePlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullscreen = true

                // The video will continue playing in fullscreenView
                youTubePlayerView.visibility = View.GONE
                fullscreenViewContainer.visibility = View.VISIBLE
                fullscreenViewContainer.addView(fullscreenView)

                // Optionally request landscape orientation
                // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onExitFullscreen() {
                isFullscreen = false

                // The video will continue playing in the player
                youTubePlayerView.visibility = View.VISIBLE
                fullscreenViewContainer.visibility = View.GONE
                fullscreenViewContainer.removeAllViews()
            }
        })
        //End Fullscreen code

        //Adding the youtube listener so we can change the video
        youTubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(player: YouTubePlayer) {
                this@DisplaySelectedUserWorkout.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        //Adding button listeners
        addButtonListeners(AllExerciseLists.currentSelectedWorkout.second, buttonList)

        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        val scheduleWorkoutButton = view.findViewById<AppCompatButton>(R.id.scheduleWorkoutButton)
        val setScheduleButton = view.findViewById<AppCompatButton>(R.id.setScheduleButton)
        val dateAndTimeLayout = view.findViewById<LinearLayout>(R.id.dateAndTimeLayout)

        //Button to show date and time pickers
        scheduleWorkoutButton.setOnClickListener {
            if(dateAndTimeLayout.visibility == View.GONE){
                dateAndTimeLayout.visibility = View.VISIBLE
            }
            else{
                dateAndTimeLayout.visibility = View.GONE
            }
        }

        setScheduleButton.setOnClickListener {
            val currentUser = FirebaseAuth.getInstance().currentUser

            // Get the selected date from the DatePicker
            val selectedYear = datePicker.year
            val selectedMonth = datePicker.month
            val selectedDayOfMonth = datePicker.dayOfMonth
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute

            //Code to save the Workout
            if (currentUser != null) {
                //Get a reference to the Firestore database
                val db = FirebaseFirestore.getInstance()
                //Get a reference to the user's document
                val userDocumentReference = db.collection("users").document(currentUser.uid)

                // Create a Calendar instance and set the selected date and time
                val calendar = Calendar.getInstance()
                calendar.set(
                    selectedYear,
                    selectedMonth,
                    selectedDayOfMonth,
                    selectedHour,
                    selectedMinute
                )
                val selectedDate = calendar.time    // Convert the Calendar instance to a Date
                val timestamp = Timestamp(selectedDate) // Convert the Date to a Timestamp

                //Saving the schedule
                userDocumentReference.collection("Scheduled Workouts")
                    .document(AllExerciseLists.currentSelectedWorkout.first)
                    .set(mapOf("timestampField" to timestamp))
                Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireActivity(), "Error, User could not be authenticated", Toast.LENGTH_LONG).show()
            }
            dateAndTimeLayout.visibility = View.GONE
        }

        //Delete workout button
        val deleteWorkoutButton = view.findViewById<AppCompatButton>(R.id.deleteWorkoutButton)
        val confirmDeleteButton = view.findViewById<AppCompatButton>(R.id.confirmDeleteButton)
        val cancelDeleteButton = view.findViewById<AppCompatButton>(R.id.cancelDeleteButton)
        val deleteLayout = view.findViewById<LinearLayout>(R.id.deleteLayout)

        //Show delete layout
        deleteWorkoutButton.setOnClickListener {
            if(deleteLayout.visibility == View.GONE){
                deleteLayout.visibility = View.VISIBLE
            }
            else{
                deleteLayout.visibility = View.GONE
            }
        }
        //Cancel the deletion
        cancelDeleteButton.setOnClickListener {
            deleteLayout.visibility = View.GONE
        }
        //Confirm the deletion
        confirmDeleteButton.setOnClickListener {
            val currentUser = FirebaseAuth.getInstance().currentUser

            //Code to save the Workout
            if (currentUser != null) {
                //Get a reference to the Firestore database
                val db = FirebaseFirestore.getInstance()
                //Getting all our references that we need to access
                val userDocumentReference = db.collection("users").document(currentUser.uid)
                val workoutCollectionReference = userDocumentReference.collection(AllExerciseLists.currentSelectedWorkout.first)
                val workoutScheduleReference = userDocumentReference.collection("Scheduled Workouts").document(AllExerciseLists.currentSelectedWorkout.first)

                //Removing the the workout from our collections list
                userDocumentReference.get()
                    .addOnSuccessListener { documentSnapshot ->
                        if(documentSnapshot.exists()){
                            if (documentSnapshot.contains("Collections")) {
                                // The field exists in the document
                                val collectionText = documentSnapshot.getString("Collections").toString()
                                var collectionList = collectionText.split(", ").toMutableList()
                                collectionList.remove(AllExerciseLists.currentSelectedWorkout.first)
                                val myString = collectionList.joinToString(", ")
                                val newCollections = hashMapOf<String, Any>(
                                    "Collections" to myString
                                )
                                userDocumentReference.update(newCollections)
                            } else {
                                Toast.makeText(requireActivity(), "Error, Could not find workout in collection list", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                //Deleting any scheduled workouts
                workoutScheduleReference
                    .delete()


                //Deleting the user workout
                workoutCollectionReference
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        for (document in querySnapshot) {
                            document.reference.delete()
                        }

                        // Now, delete the collection
                        workoutCollectionReference
                            .document()
                            .delete()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireActivity(), "Error, Could not find workout collection", Toast.LENGTH_LONG).show()
                    }

                Toast.makeText(requireActivity(), "Success, Your workout has been deleted", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_DisplaySelectedUserWorkout_to_HomePage)
            }
            else{
                Toast.makeText(requireActivity(), "Error, User could not be authenticated", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}