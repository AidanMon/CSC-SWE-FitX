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
import com.example.fitx.databinding.UserWorkoutsDisplayFragBinding
import com.example.fitx.repository.AllExerciseLists
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class UserWorkoutsDisplay : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: UserWorkoutsDisplayFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var textViewList = mutableListOf<TextView>()
    private var buttonList = mutableListOf<Button>()
    private var workoutNameList = mutableListOf<String>()

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
    private fun createAllViews(workoutNameList: MutableList<String>, parentLayout: ConstraintLayout, textViewList: MutableList<TextView>,
                               buttonList: MutableList<Button>){
        //Creating the TextViews and all Buttons for the leg exercises
        for(workout in workoutNameList){
            //Creating out TextViews
            val newTextView = TextView(requireContext())
            parentLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = setStringMaxLength(workout,16)// Change the text
            newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
            newTextView.setPadding(11,21,0,0)
            newTextView.height = textHeightValue.toInt()
            newTextView.width = textWidthValue.toInt()
            newTextView.setTextColor(Color.BLACK)
            //newTextView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
            newTextView.setBackgroundResource(R.drawable.user_workout_border)
            textViewList.add(newTextView)

            //Creating our buttons
            val newButton = Button(requireContext())
            parentLayout.addView(newButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            newButton.id = View.generateViewId()
            newButton.text = "Display Workout"                                  // Change the text
            newButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 33f)  //Text size
            newButton.gravity = Gravity.CENTER                          //Text centered
            newButton.height = buttonHeightValue.toInt()                //Text height
            newButton.width = buttonWidthValue.toInt()                  //Text width
            newButton.setTextColor(Color.WHITE)                         //Text color
            newButton.setBackgroundResource(R.drawable.rounded_button)
            buttonList.add(newButton)
        }
    }

    //Function set the constraints of our added items
    private fun setConstraint(constraintLayout: ConstraintLayout, layoutID: Int, textViewList: MutableList<TextView>,
                              buttonList: MutableList<Button>){
        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        //Constraint for first TextView
        constraintSet.connect(textViewList[0].id, ConstraintSet.TOP, R.id.userWorkoutsDisplayHeader, ConstraintSet.BOTTOM, 42)
        constraintSet.connect(textViewList[0].id, ConstraintSet.LEFT, layoutID,ConstraintSet.LEFT)

        //Constraint for first Button
        constraintSet.connect(buttonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(buttonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 6)


        //Constraints for the remainder of the exercises
        for(i in 1 until textViewList.size){
            //TextView constraints
            constraintSet.connect(textViewList[i].id, ConstraintSet.TOP, textViewList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[i].id, ConstraintSet.LEFT, layoutID,ConstraintSet.LEFT)

            //Button constraints
            constraintSet.connect(buttonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(buttonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 6)
        }

        constraintSet.applyTo(constraintLayout)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        textViewList.clear()
        buttonList.clear()
        workoutNameList.clear()

        _binding = UserWorkoutsDisplayFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.user_workouts_display_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        for(workout in AllExerciseLists.userMadeWorkouts){
            workoutNameList.add(workout.first)
        }

        createAllViews(workoutNameList,parentLayout,textViewList,buttonList)
        setConstraint(parentLayout, R.id.parentLayout, textViewList,buttonList)

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



        for (i in 0 until workoutNameList.size){
            buttonList[i].setOnClickListener {
                AllExerciseLists.currentSelectedWorkout = AllExerciseLists.userMadeWorkouts[i]
                findNavController().navigate(R.id.action_UserWorkoutsDisplay_to_DisplaySelectedUserWorkouts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}