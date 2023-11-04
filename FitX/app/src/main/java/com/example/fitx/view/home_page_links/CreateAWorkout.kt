package com.example.fitx.view.home_page_links

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
import com.example.fitx.databinding.CreateAWorkoutFragBinding
import com.example.fitx.model.Exercise
import com.example.fitx.repository.AllExerciseLists
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class CreateAWorkout : Fragment() {

    //Function to truncate Exercise names that are too long
    fun setStringMaxLength(input: String, maxLength: Int): String {
        return if (input.length <= maxLength) {
            input
        } else {
            input.substring(0, maxLength) + "."
        }
    }

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: CreateAWorkoutFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Creating my different exercise lists as well as their button and text view lists
    private var legExercises = mutableListOf<Exercise>()
    private var legTextViewList = mutableListOf<TextView>()
    private var legButtonList = mutableListOf<Button>()
    private var chestExercises = mutableListOf<Exercise>()
    private var chestTextViewList = mutableListOf<TextView>()
    private var chestButtonList = mutableListOf<Button>()
    private var backExercises = mutableListOf<Exercise>()
    private var backTextViewList = mutableListOf<TextView>()
    private var backButtonList = mutableListOf<Button>()
    private var armExercises = mutableListOf<Exercise>()
    private var armTextViewList = mutableListOf<TextView>()
    private var armButtonList = mutableListOf<Button>()
    private var coreExercises = mutableListOf<Exercise>()
    private var coreTextViewList = mutableListOf<TextView>()
    private var coreButtonList = mutableListOf<Button>()

    //List for user made workout
    private var legsAddToWorkoutList = mutableListOf<Button>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = CreateAWorkoutFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.create_a_workout_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        legExercises = mutableListOf<Exercise>()
        legTextViewList = mutableListOf<TextView>()
        legButtonList = mutableListOf<Button>()
        chestExercises = mutableListOf<Exercise>()
        chestTextViewList = mutableListOf<TextView>()
        chestButtonList = mutableListOf<Button>()
        backExercises = mutableListOf<Exercise>()
        backTextViewList = mutableListOf<TextView>()
        backButtonList = mutableListOf<Button>()
        armExercises = mutableListOf<Exercise>()
        armTextViewList = mutableListOf<TextView>()
        armButtonList = mutableListOf<Button>()
        coreExercises = mutableListOf<Exercise>()
        coreTextViewList = mutableListOf<TextView>()
        coreButtonList = mutableListOf<Button>()
        legsAddToWorkoutList = mutableListOf<Button>()

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

        for(list in AllExerciseLists.exerciseList){
            val muscleGroups = list.muscleGroup.split("/")      //Finding all muscle groups for an exercise
            //Adding them to our lists
            for(item in muscleGroups){
                when(item){
                    "Legs" -> legExercises.add(list)
                    "Chest" -> chestExercises.add(list)
                    "Back" -> backExercises.add(list)
                    "Arms" -> armExercises.add(list)
                    "Core" -> coreExercises.add(list)
                }
            }
        }

        val legConstraintLayout = rootView.findViewById<ConstraintLayout>(R.id.legsLayout)

        //Creating the TextViews and all Buttons for the exercises
        for(exercise in legExercises){
            //Creating out TextViews
            val newTextView = TextView(requireContext())
            legConstraintLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = setStringMaxLength(exercise.exerciseName,16)// Change the text
            newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
            newTextView.setPadding(11,21,0,0)
            newTextView.height = textHeightValue.toInt()
            newTextView.width = textWidthValue.toInt()
            newTextView.setTextColor(Color.BLACK)
            //newTextView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
            newTextView.setBackgroundResource(R.drawable.exercise_border)
            legTextViewList.add(newTextView)

            //Creating our buttons
            val newButton = Button(requireContext())
            legConstraintLayout.addView(newButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            newButton.id = View.generateViewId()
            newButton.text = "Example"                                  // Change the text
            newButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            newButton.gravity = Gravity.CENTER                          //Text centered
            newButton.height = buttonHeightValue.toInt()                //Text height
            newButton.width = buttonWidthValue.toInt()                  //Text width
            newButton.setTextColor(Color.WHITE)                         //Text color
            newButton.setBackgroundResource(R.drawable.rounded_button)
            legButtonList.add(newButton)

            //Creating our add buttons
            val addButton = Button(requireContext())
            legConstraintLayout.addView(addButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            addButton.id = View.generateViewId()
            addButton.text = "Add Exercise"                                  // Change the text
            addButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            addButton.setPadding(0,0,0,5)                        //Text centered
            addButton.height = buttonHeightValue.toInt()                //Text height
            addButton.width = buttonWidthValue.toInt()                  //Text width
            addButton.setTextColor(Color.WHITE)                         //Text color
            addButton.setBackgroundResource(R.drawable.rounded_button)
            legsAddToWorkoutList.add(addButton)
        }

        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(legConstraintLayout)

        //Constraint for first TextView
        constraintSet.connect(legTextViewList[0].id, ConstraintSet.TOP, R.id.legsLayout, ConstraintSet.TOP, 21)
        constraintSet.connect(legTextViewList[0].id, ConstraintSet.LEFT, R.id.legsLayout,ConstraintSet.LEFT)

        //Constraint for first Button
        constraintSet.connect(legButtonList[0].id, ConstraintSet.TOP, legTextViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(legButtonList[0].id, ConstraintSet.RIGHT, legTextViewList[0].id, ConstraintSet.RIGHT, 6)

        //Constraint for add exercise Button
        constraintSet.connect(legsAddToWorkoutList[0].id, ConstraintSet.TOP, legTextViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(legsAddToWorkoutList[0].id, ConstraintSet.RIGHT, legTextViewList[0].id, ConstraintSet.RIGHT, 250)

        //Constraints for the remainder of the exercises
        for(i in 1 until legTextViewList.size){
            //TextView constraints
            constraintSet.connect(legTextViewList[i].id, ConstraintSet.TOP, legTextViewList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(legTextViewList[i].id, ConstraintSet.LEFT, R.id.legsLayout,ConstraintSet.LEFT)

            //Button constraints
            constraintSet.connect(legButtonList[i].id, ConstraintSet.TOP, legTextViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(legButtonList[i].id, ConstraintSet.RIGHT, legTextViewList[i].id, ConstraintSet.RIGHT, 6)

            //Constraint for add exercise Button
            constraintSet.connect(legsAddToWorkoutList[i].id, ConstraintSet.TOP, legTextViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(legsAddToWorkoutList[i].id, ConstraintSet.RIGHT, legTextViewList[i].id, ConstraintSet.RIGHT, 250)
        }


        constraintSet.applyTo(legConstraintLayout)

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
                this@CreateAWorkout.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        //Adding the button listeners for the exercise videos
        for (i in 0 until legExercises.size){
            legButtonList[i].setOnClickListener {
                changeVideo(legExercises[i].videoID, youTubePlayerView)
            }
        }

        //Adding show all buttons
        val legsButton = view.findViewById<AppCompatButton>(R.id.legsShowAll)
        val myConstraintLayout = view.findViewById<ConstraintLayout>(R.id.legsLayout)
        val legsText = view.findViewById<TextView>(R.id.legsText)

        legsButton.setOnClickListener {
            if (myConstraintLayout.visibility == View.GONE) {
                myConstraintLayout.visibility = View.VISIBLE
                legsText.setBackgroundResource(R.drawable.triple_border_bottom_gone)
            } else {
                myConstraintLayout.visibility = View.GONE
                legsText.setBackgroundResource(R.drawable.workout_border)
            }
        }

        //Button listeners for the add to buttons
        for (i in 0 until legsAddToWorkoutList.size){
            legsAddToWorkoutList[i].setOnClickListener {
                AllExerciseLists.currentCreateWorkout.add(legExercises[i])
            }
        }

        //Show created workout button
        val toCurrentCreatedWorkoutButton = view.findViewById<AppCompatButton>(R.id.showCreatedWorkout)
        toCurrentCreatedWorkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_CreateAWorkout_to_CurrentCreatedWorkout)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}