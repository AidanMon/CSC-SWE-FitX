package com.example.fitx.view.created_workouts

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.CurrentCreatedWorkoutFragBinding
import com.example.fitx.repository.AllExerciseLists
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class CurrentCreatedWorkout : Fragment() {
    //TODO(Fix error that removes constraints when coming from the save workout fragment)

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: CurrentCreatedWorkoutFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //List of Buttons and TextViews
    private var textViewList = mutableListOf<TextView>()
    private var buttonList = mutableListOf<Button>()
    private var removeButtonList = mutableListOf<Button>()

    //Save button
    private var saveButtonList = mutableListOf<AppCompatButton>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = CurrentCreatedWorkoutFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.current_created_workout_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        //Calculating our height and width for our different items
        //TextView
        var dpValue = 64
        val textHeightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )
        dpValue = 380
        val textWidthValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )
        //Button
        dpValue = 40
        val buttonHeightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )
        dpValue = 80
        val buttonWidthValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )

        //Creating the TextViews and all Buttons for the exercises
        for(exercise in AllExerciseLists.currentCreateWorkout){
            //Creating our TextViews
            val newTextView = TextView(requireContext())
            parentLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = exercise.exerciseName// Change the text
            newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
            newTextView.setPadding(11,21,0,0)
            newTextView.height = textHeightValue.toInt()
            newTextView.width = textWidthValue.toInt()
            newTextView.setTextColor(Color.BLACK)
            newTextView.setBackgroundResource(R.drawable.exercise_border)
            textViewList.add(newTextView)

            //Creating our buttons
            val newButton = Button(requireContext())
            parentLayout.addView(newButton)
            newButton.id = View.generateViewId()
            newButton.text = "Example"                                  // Change the text
            newButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            newButton.gravity = Gravity.CENTER                          //Text centered
            newButton.height = buttonHeightValue.toInt()                //Text height
            newButton.width = buttonWidthValue.toInt()                  //Text width
            newButton.setTextColor(Color.WHITE)                         //Text color
            newButton.setBackgroundResource(R.drawable.rounded_button)
            buttonList.add(newButton)

            //Creating our add buttons
            val addButton = Button(requireContext())
            parentLayout.addView(addButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            addButton.id = View.generateViewId()
            addButton.text = "Remove Exercise"                                  // Change the text
            addButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            addButton.setPadding(0,0,0,5)                        //Text centered
            addButton.height = buttonHeightValue.toInt()                //Text height
            addButton.width = buttonWidthValue.toInt()                  //Text width
            addButton.setTextColor(Color.WHITE)                         //Text color
            addButton.setBackgroundResource(R.drawable.rounded_button)
            removeButtonList.add(addButton)
        }

        val saveButton = rootView.findViewById<AppCompatButton>(R.id.saveCreatedWorkout)
        saveButtonList.add(saveButton)
        val emptyTextView = TextView(requireContext())

        //If there are no exercises inform the user and TODO(Ensure you cannot save the workout)
        if(AllExerciseLists.currentCreateWorkout.size == 0){
            parentLayout.addView(emptyTextView)
            emptyTextView.id = View.generateViewId()
            emptyTextView.text = "To begin, add some exercises"// Change the text
            emptyTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 128f)
            emptyTextView.setPadding(11,21,0,0)
            emptyTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            emptyTextView.height = 350
            emptyTextView.width = textWidthValue.toInt()
            emptyTextView.setTextColor(Color.BLACK)
            textViewList.add(emptyTextView)
        }
        else{
            //Show save button if there are exercises
            saveButton.visibility = View.VISIBLE
        }

        //Creating our bottom padding object
        val paddingBelow = TextView(requireContext())
        parentLayout.addView(paddingBelow)
        paddingBelow.id = View.generateViewId()
        paddingBelow.height = textHeightValue.toInt()
        paddingBelow.width = textWidthValue.toInt()

        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(parentLayout)

        //Checking to ensure the list is not empty
        if(AllExerciseLists.currentCreateWorkout.size != 0){
            //Constraint for first TextView
            constraintSet.connect(textViewList[0].id, ConstraintSet.TOP, R.id.enterFullScreenButton, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[0].id, ConstraintSet.LEFT, R.id.parentLayout,ConstraintSet.LEFT)

            //Constraint for first Button
            constraintSet.connect(buttonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
            constraintSet.connect(buttonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 6)

            //Constraint for add exercise Button
            constraintSet.connect(removeButtonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
            constraintSet.connect(removeButtonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 250)
        }
        //If there are no exercises set constraints
        else{
            constraintSet.connect(emptyTextView.id, ConstraintSet.TOP, R.id.enterFullScreenButton, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(emptyTextView.id, ConstraintSet.LEFT, R.id.parentLayout,ConstraintSet.LEFT)
        }

        //Constraints for the remainder of the exercises
        for(i in 1 until textViewList.size){
            //TextView constraints
            constraintSet.connect(textViewList[i].id, ConstraintSet.TOP, textViewList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[i].id, ConstraintSet.LEFT, R.id.legsLayout,ConstraintSet.LEFT)

            //Button constraints
            constraintSet.connect(buttonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(buttonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 6)

            //Constraint for add exercise Button
            constraintSet.connect(removeButtonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(removeButtonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 250)
        }


        constraintSet.applyTo(parentLayout)



        //Bottom padding values and constraint setting
        var paddingValue = 600                  //Excess space to leave room for fullscreen container
        val paddingHeightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, paddingValue.toFloat(), Resources.getSystem().displayMetrics
        )
        //Checking to ensure the list is not empty
        if(AllExerciseLists.currentCreateWorkout.size != 0){
            constraintSet.connect(paddingBelow.id, ConstraintSet.TOP, textViewList[AllExerciseLists.currentCreateWorkout.size - 1].id, ConstraintSet.BOTTOM, paddingHeightValue.toInt())
            constraintSet.connect(paddingBelow.id, ConstraintSet.LEFT, R.id.parentLayout, ConstraintSet.LEFT)
        }

        // Apply the constraints
        constraintSet.applyTo(parentLayout)

        // Return the rootView of the fragment
        return rootView
    }

    //Function to change video
    private fun changeVideo(videoId: String, player: YouTubePlayerView){
        player.visibility = View.VISIBLE
        youTubePlayer?.loadVideo(videoId, 0F)
    }

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
                this@CurrentCreatedWorkout.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        //Setting youtube play buttons
        for ((i, exercise) in AllExerciseLists.currentCreateWorkout.withIndex()){
            buttonList[i].setOnClickListener {
                changeVideo(exercise.videoID, youTubePlayerView)
            }
        }

        //Button listeners for the remove buttons
        for ((i, exercise) in AllExerciseLists.currentCreateWorkout.withIndex()){
            removeButtonList[i].setOnClickListener {
                AllExerciseLists.currentCreateWorkout.remove(exercise)
                removeButtonList[i].visibility = View.GONE
                textViewList[i].visibility = View.GONE
                buttonList[i].visibility = View.GONE
                if(AllExerciseLists.currentCreateWorkout.size == 0){
                    saveButtonList[0].visibility = View.GONE
                }
            }
        }

        //Navigation to save the workout
        val toSaveWorkoutButton = view.findViewById<AppCompatButton>(R.id.saveCreatedWorkout)
        toSaveWorkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_CurrentCreatedWorkout_to_SaveWorkout)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}