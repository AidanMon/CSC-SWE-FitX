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
    private var legAddToWorkoutList = mutableListOf<Button>()
    private var chestAddToWorkoutList = mutableListOf<Button>()
    private var backAddToWorkoutList = mutableListOf<Button>()
    private var armAddToWorkoutList = mutableListOf<Button>()
    private var coreAddToWorkoutList = mutableListOf<Button>()

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
    private fun createAllViews(exerciseList: MutableList<Exercise>, constraintLayout: ConstraintLayout, textViewList: MutableList<TextView>,
                               buttonList: MutableList<Button>, addToWorkoutList: MutableList<Button>){
        //Creating the TextViews and all Buttons for the leg exercises
        for(exercise in exerciseList){
            //Creating out TextViews
            val newTextView = TextView(requireContext())
            constraintLayout.addView(newTextView)
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
            constraintLayout.addView(newButton)
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

            //Creating our add buttons
            val addButton = Button(requireContext())
            constraintLayout.addView(addButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            addButton.id = View.generateViewId()
            addButton.text = "Add Exercise"                                  // Change the text
            addButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            addButton.setPadding(0,0,0,5)                        //Text centered
            addButton.height = buttonHeightValue.toInt()                //Text height
            addButton.width = buttonWidthValue.toInt()                  //Text width
            addButton.setTextColor(Color.WHITE)                         //Text color
            addButton.setBackgroundResource(R.drawable.rounded_button)
            addToWorkoutList.add(addButton)
        }
    }

    //Function set the constraints of our added items
    private fun setConstraint(constraintLayout: ConstraintLayout, layoutID: Int, textViewList: MutableList<TextView>,
                              buttonList: MutableList<Button>, addToWorkoutList: MutableList<Button>){
        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        //Constraint for first TextView
        constraintSet.connect(textViewList[0].id, ConstraintSet.TOP, layoutID, ConstraintSet.TOP, 21)
        constraintSet.connect(textViewList[0].id, ConstraintSet.LEFT, layoutID,ConstraintSet.LEFT)

        //Constraint for first Button
        constraintSet.connect(buttonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(buttonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 6)

        //Constraint for add exercise Button
        constraintSet.connect(addToWorkoutList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(addToWorkoutList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 250)

        //Constraints for the remainder of the exercises
        for(i in 1 until textViewList.size){
            //TextView constraints
            constraintSet.connect(textViewList[i].id, ConstraintSet.TOP, textViewList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[i].id, ConstraintSet.LEFT, layoutID,ConstraintSet.LEFT)

            //Button constraints
            constraintSet.connect(buttonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(buttonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 6)

            //Constraint for add exercise Button
            constraintSet.connect(addToWorkoutList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(addToWorkoutList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 250)
        }

        constraintSet.applyTo(constraintLayout)


    }

    //Function to add all of the button listeners
    private fun addButtonListeners(view: View, exerciseList: MutableList<Exercise>, buttonList: MutableList<Button>,
                                   addToWorkoutList: MutableList<Button>, showAll: Int, layoutID: Int,
                                   textViewID: Int){
        //Adding the button listeners for the exercise videos
        for (i in 0 until exerciseList.size){
            buttonList[i].setOnClickListener {
                changeVideo(exerciseList[i].videoID, youTubePlayerView)
            }
        }

        //Adding show all buttons
        val button = view.findViewById<AppCompatButton>(showAll)
        val myConstraintLayout = view.findViewById<ConstraintLayout>(layoutID)
        val textView = view.findViewById<TextView>(textViewID)

        button.setOnClickListener {
            if (myConstraintLayout.visibility == View.GONE) {
                myConstraintLayout.visibility = View.VISIBLE
                textView.setBackgroundResource(R.drawable.triple_border_bottom_gone)
            } else {
                myConstraintLayout.visibility = View.GONE
                textView.setBackgroundResource(R.drawable.workout_border)
            }
        }

        //Button listeners for the add to buttons
        for (i in 0 until addToWorkoutList.size){
            addToWorkoutList[i].setOnClickListener {
                AllExerciseLists.currentCreateWorkout.add(exerciseList[i])
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = CreateAWorkoutFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.create_a_workout_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        //Zeroing out all the lists so we don't get copies
        legExercises = mutableListOf()
        legTextViewList = mutableListOf()
        legButtonList = mutableListOf()
        legAddToWorkoutList = mutableListOf()
        chestExercises = mutableListOf()
        chestTextViewList = mutableListOf()
        chestButtonList = mutableListOf()
        chestAddToWorkoutList = mutableListOf()
        backExercises = mutableListOf()
        backTextViewList = mutableListOf()
        backButtonList = mutableListOf()
        backAddToWorkoutList = mutableListOf()
        armExercises = mutableListOf()
        armTextViewList = mutableListOf()
        armButtonList = mutableListOf()
        armAddToWorkoutList = mutableListOf()
        coreExercises = mutableListOf()
        coreTextViewList = mutableListOf()
        coreButtonList = mutableListOf()
        coreAddToWorkoutList = mutableListOf()

        //Calculating our height and width for our different items
        //TextView

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

        //Leg Views added
        val legConstraintLayout = rootView.findViewById<ConstraintLayout>(R.id.legsLayout)
        createAllViews(legExercises, legConstraintLayout, legTextViewList, legButtonList, legAddToWorkoutList)
        setConstraint(legConstraintLayout, R.id.legsLayout, legTextViewList, legButtonList, legAddToWorkoutList)

        //Chest Views added
        val chestConstraintLayout = rootView.findViewById<ConstraintLayout>(R.id.chestLayout)
        createAllViews(chestExercises, chestConstraintLayout, chestTextViewList, chestButtonList, chestAddToWorkoutList)
        setConstraint(chestConstraintLayout, R.id.chestLayout, chestTextViewList, chestButtonList, chestAddToWorkoutList)

        //Back Views added
        val backConstraintLayout = rootView.findViewById<ConstraintLayout>(R.id.backLayout)
        createAllViews(backExercises, backConstraintLayout, backTextViewList, backButtonList, backAddToWorkoutList)
        setConstraint(backConstraintLayout, R.id.backLayout, backTextViewList, backButtonList, backAddToWorkoutList)

        //Arm Views added
        val armConstraintLayout = rootView.findViewById<ConstraintLayout>(R.id.armsLayout)
        createAllViews(armExercises, armConstraintLayout, armTextViewList, armButtonList, armAddToWorkoutList)
        setConstraint(armConstraintLayout, R.id.armsLayout, armTextViewList, armButtonList, armAddToWorkoutList)

        //Core Views added
        val coreConstraintLayout = rootView.findViewById<ConstraintLayout>(R.id.coreLayout)
        createAllViews(coreExercises, coreConstraintLayout, coreTextViewList, coreButtonList, coreAddToWorkoutList)
        setConstraint(coreConstraintLayout, R.id.coreLayout, coreTextViewList, coreButtonList, coreAddToWorkoutList)

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

        addButtonListeners(view, legExercises, legButtonList, legAddToWorkoutList, R.id.legsShowAll, R.id.legsLayout, R.id.legsText)
        addButtonListeners(view, chestExercises, chestButtonList, chestAddToWorkoutList, R.id.chestShowAll, R.id.chestLayout, R.id.chestText)
        addButtonListeners(view, backExercises, backButtonList, backAddToWorkoutList, R.id.backShowAll, R.id.backLayout, R.id.backText)
        addButtonListeners(view, armExercises, armButtonList, armAddToWorkoutList, R.id.armsShowAll, R.id.armsLayout, R.id.armsText)
        addButtonListeners(view, coreExercises, coreButtonList, coreAddToWorkoutList, R.id.coreShowAll, R.id.coreLayout, R.id.coreText)

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