package com.example.fitx.view.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitx.R
import com.example.fitx.databinding.RecommendationFragBinding
import com.example.fitx.repository.AllExerciseLists
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Suppress("NAME_SHADOWING")
class Recommendation : Fragment() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: RecommendationFragBinding? = null
    private val binding get() = _binding!!

    var buttonsList = mutableListOf<Button>()
    var textList = mutableListOf<TextView>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecommendationFragBinding.inflate(inflater, container, false)

        return binding.root
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
                this@Recommendation.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })
        var btnName = mutableListOf<Int>(R.id.Video1,R.id.Video2,R.id.Video3,R.id.Video4,R.id.Video5,R.id.Video6,R.id.Video7,R.id.Video8,)
        var exerciseName = mutableListOf<Int>(R.id.Exercise1,R.id.Exercise2,R.id.Exercise3,R.id.Exercise4,R.id.Exercise5,R.id.Exercise6,R.id.Exercise7,R.id.Exercise8,)


        for (i in 7..14) {
            val Button = view.findViewById<Button>(btnName[i-7])
            buttonsList.add(Button)
            val Text = view.findViewById<TextView>(exerciseName[i-7])
            textList.add(Text)
            buttonsList[i-7].setOnClickListener {
                //TODO(getVideo ID from database)
                changeVideo(AllExerciseLists.exerciseList[i].videoID, youTubePlayerView)
            }
            textList[i-7].text = AllExerciseLists.exerciseList[i].exerciseName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }

}

