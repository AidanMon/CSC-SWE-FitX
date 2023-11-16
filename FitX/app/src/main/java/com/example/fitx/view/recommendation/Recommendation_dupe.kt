package com.example.fitx.view.recommendation

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.fitx.R
import com.example.fitx.databinding.RecommendationFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Suppress("NAME_SHADOWING")
class Recommendation_dupe : Fragment() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    //List of Buttons and TextViews
    private val parentTextViewList = ArrayList<TextViews> ()

    private var _binding: RecommendationFragBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    data class TextViews(
        var TextViewList: MutableList<TextView>,
        var ButtonList: MutableList<Button>
    )

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //getWorkout(userInput, "Beginner")
        _binding = RecommendationFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.recommendation_frag, container, false)
        val recParentLayout = rootView.findViewById<ConstraintLayout>(R.id.recParentLayout)

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
       /* for (j in 0..1) {
            val newTextView = TextView(requireContext())
            recParentLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = WorkoutsList.workouts[j].workoutName// Change the text
            newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
            newTextView.setPadding(11, 21, 0, 0)
            newTextView.height = textHeightValue.toInt()
            newTextView.width = textWidthValue.toInt()
            newTextView.setTextColor(Color.BLACK)
            //newTextView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
            newTextView.setBackgroundResource(R.drawable.exercise_border)
            parentTextViewList[j].TextViewer = newTextView

            for (i in 0 until WorkoutsList.workouts[j].exerciseList.size) {
                //Creating out TextViews
                val newTextView = TextView(requireContext())
                recParentLayout.addView(newTextView)
                newTextView.id = View.generateViewId()
                newTextView.text =
                    WorkoutsList.workouts[j].exerciseList[i].exerciseName// Change the text
                newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
                newTextView.setPadding(11, 21, 0, 0)
                newTextView.height = textHeightValue.toInt()
                newTextView.width = textWidthValue.toInt()
                newTextView.setTextColor(Color.BLACK)
                //newTextView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
                newTextView.setBackgroundResource(R.drawable.exercise_border)
                parentTextViewList[j].TextViewList.add(newTextView)

                //Creating our buttons
                val newButton = Button(requireContext())
                recParentLayout.addView(newButton)
                //newButton.tag = (element.exerciseName + "Video")            //Just the tag
                newButton.id = View.generateViewId()
                newButton.text = "Example"                                  // Change the text
                newButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
                newButton.gravity = Gravity.CENTER                          //Text centered
                newButton.height = buttonHeightValue.toInt()                //Text height
                newButton.width = buttonWidthValue.toInt()                  //Text width
                newButton.setTextColor(Color.WHITE)                         //Text color
                newButton.setBackgroundResource(R.drawable.rounded_button)
                parentTextViewList[j].ButtonList.add(newButton)
            }
        }

//Creating our bottom padding object
        val paddingBelow = TextView(requireContext())
        recParentLayout.addView(paddingBelow)
        paddingBelow.id = View.generateViewId()
        paddingBelow.height = textHeightValue.toInt()
        paddingBelow.width = textWidthValue.toInt()


//Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(recParentLayout)

//Constraint for first TextView
        for (j in 0 until (parentTextViewList.size)) {
            if (j == 0) {
                constraintSet.connect(
                    parentTextViewList[j].TextViewer.id,
                    ConstraintSet.TOP,
                    R.id.enterFullScreenButton,
                    ConstraintSet.BOTTOM,
                    21
                )
                constraintSet.connect(
                    parentTextViewList[j].TextViewer.id, ConstraintSet.LEFT, R.id.recParentLayout,
                    ConstraintSet.LEFT
                )
            } else {
                constraintSet.connect(
                    parentTextViewList[j].TextViewer.id,
                    ConstraintSet.TOP,
                    parentTextViewList[j - 1].TextViewer.id,
                    ConstraintSet.BOTTOM,
                    21
                )
                constraintSet.connect(
                    parentTextViewList[j].TextViewer.id,
                    ConstraintSet.LEFT,
                    R.id.recParentLayout,
                    ConstraintSet.LEFT
                )
            }
            //Constraints for the remainder of the exercises
            for (i in 0 until parentTextViewList[j].TextViewList.size) {
                if (i == 0) {
                    constraintSet.connect(
                        parentTextViewList[j].TextViewList[i].id,
                        ConstraintSet.TOP,
                        parentTextViewList[j].TextViewer.id,
                        ConstraintSet.BOTTOM,
                        21
                    )
                } else {
                    constraintSet.connect(
                        parentTextViewList[j].TextViewList[i].id,
                        ConstraintSet.TOP,
                        parentTextViewList[j].TextViewList[i - 1].id,
                        ConstraintSet.BOTTOM,
                        21
                    )
                }
                constraintSet.connect(
                    parentTextViewList[j].TextViewList[i].id,
                    ConstraintSet.LEFT,
                    R.id.recParentLayout,
                    ConstraintSet.LEFT
                )

                constraintSet.connect(
                    parentTextViewList[j].ButtonList[i].id,
                    ConstraintSet.TOP,
                    parentTextViewList[j].TextViewList[i].id,
                    ConstraintSet.TOP,
                    21
                )
                constraintSet.connect(
                    parentTextViewList[j].ButtonList[i].id,
                    ConstraintSet.RIGHT,
                    parentTextViewList[j].TextViewList[i].id,
                    ConstraintSet.RIGHT,
                    11
                )
            }
        }

//Bottom padding values and constraint setting
        val paddingValue =
            600                  //Excess space to leave room for fullscreen container
        val paddingHeightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            paddingValue.toFloat(),
            Resources.getSystem().displayMetrics
        )
        constraintSet.connect(
            paddingBelow.id,
            ConstraintSet.TOP,
            parentTextViewList[1].TextViewList[WorkoutsList.workouts[1].exerciseList.size - 1].id,
            ConstraintSet.BOTTOM,
            paddingHeightValue.toInt()
        )
        constraintSet.connect(
            paddingBelow.id,
            ConstraintSet.LEFT,
            R.id.recParentLayout,
            ConstraintSet.LEFT
        )

// Apply the constraints
        constraintSet.applyTo(recParentLayout)

// Return the rootView of the fragment*/
        return rootView
    }


//Function to change video
private fun changeVideo(videoId: String, player: YouTubePlayerView) {
player.visibility = View.VISIBLE
youTubePlayer?.loadVideo(videoId, 0F)
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)

//Initializing our youtube video player
youTubePlayerView = view.findViewById(R.id.theVideoPlayer)
lifecycle.addObserver(youTubePlayerView)

//Fullscreen code
val fullscreenViewContainer =
    view.findViewById<FrameLayout>(R.id.full_screen_view_container)
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
youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
    override fun onReady(player: YouTubePlayer) {
        this@Recommendation_dupe.youTubePlayer = youTubePlayer
        youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

        val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
        enterFullscreenButton.setOnClickListener {
            youTubePlayer?.toggleFullscreen()
        }
        youTubePlayer = player
    }
})
/*for (j in 0..1) {
    for (i in 0 until parentTextViewList?.get(j)?.ButtonList?.size!!) {
        parentTextViewList[j].ButtonList[i].setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo(workouts[j].exerciseList[i].videoID, youTubePlayerView)
        }
    }

}*/
}

/*class RecommendationAdapter(private val context : Context, private val arrayList: ArrayList<UserWorkout>) :  ArrayAdapter<UserWorkout>(context,
R.layout.workout_itemlistview, arrayList) {

override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val inflater : LayoutInflater = LayoutInflater.from(context)
    val view : View = inflater.inflate(R.layout.workout_itemlistview, null)

    val workoutName : TextView = view.findViewById(R.id.WorkoutName)

    workoutName.text = arrayList[position].workoutName

    return view
}
}

class ExerciseAdapter(private val context : Activity, private val arrayList: ArrayList<UserWorkout>) :  ArrayAdapter<UserWorkout>(context,
R.layout.exercise_item, arrayList){

override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val inflater : LayoutInflater = LayoutInflater.from(context)
    val view : View = inflater.inflate(R.layout.exercise_item, null)

    val exerciseName : TextView = view.findViewById(R.id.exerciseItem)
    val videoID : VideoView = view.findViewById(R.id.ExerciseItemVideoPlayer)
    exerciseName.text = arrayList[position].workoutName
    videoID.id = arrayList[position].exerciseList[position].videoID.toInt()

    return view
    return super.getView(position, convertView, parent)
}
}*/

override fun onDestroyView() {
super.onDestroyView()
youTubePlayerView.release()
_binding = null
}

}

