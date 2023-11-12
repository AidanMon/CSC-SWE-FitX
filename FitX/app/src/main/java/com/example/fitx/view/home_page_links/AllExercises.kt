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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.fitx.R
import com.example.fitx.databinding.AllExercisesFragBinding
import com.example.fitx.repository.AllExerciseLists
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class AllExercises : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: AllExercisesFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //List of Buttons and TextViews
    private var textViewList = mutableListOf<TextView>()
    private var buttonList = mutableListOf<Button>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AllExercisesFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.all_exercises_frag, container, false)
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
        for(i in 0 until AllExerciseLists.exerciseList.size){
            //Creating out TextViews
            val newTextView = TextView(requireContext())
            parentLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = AllExerciseLists.exerciseList[i].exerciseName// Change the text
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

        //Creating our bottom padding object
        val paddingBelow = TextView(requireContext())
        parentLayout.addView(paddingBelow)
        paddingBelow.id = View.generateViewId()
        paddingBelow.height = textHeightValue.toInt()
        paddingBelow.width = textWidthValue.toInt()


        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(parentLayout)

        //Constraint for first TextView
        constraintSet.connect(textViewList[0].id, ConstraintSet.TOP, R.id.enterFullScreenButton, ConstraintSet.BOTTOM, 21)
        constraintSet.connect(textViewList[0].id, ConstraintSet.LEFT, R.id.parentLayout,ConstraintSet.LEFT)

        //Constraint for first Button
        constraintSet.connect(buttonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(buttonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 11)

        //Constraints for the remainder of the exercises
        for(i in 1 until AllExerciseLists.exerciseList.size){
            constraintSet.connect(textViewList[i].id, ConstraintSet.TOP, textViewList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[i].id, ConstraintSet.LEFT, R.id.parentLayout,ConstraintSet.LEFT)

            constraintSet.connect(buttonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(buttonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 11)
        }


        //Bottom padding values and constraint setting
        var paddingValue = 600                  //Excess space to leave room for fullscreen container
        val paddingHeightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, paddingValue.toFloat(), Resources.getSystem().displayMetrics
        )
        constraintSet.connect(paddingBelow.id, ConstraintSet.TOP, textViewList[AllExerciseLists.exerciseList.size - 1].id, ConstraintSet.BOTTOM, paddingHeightValue.toInt())
        constraintSet.connect(paddingBelow.id, ConstraintSet.LEFT, R.id.parentLayout, ConstraintSet.LEFT)

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
                this@AllExercises.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        for (i in 0 until buttonList.size){
            buttonList[i].setOnClickListener {
                //TODO(getVideo ID from database)
                changeVideo(AllExerciseLists.exerciseList[i].videoID, youTubePlayerView)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}