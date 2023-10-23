package com.example.fitx.view.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.fitx.R
import com.example.fitx.databinding.SwimmingWorkoutsFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class SwimmingWorkouts : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: SwimmingWorkoutsFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SwimmingWorkoutsFragBinding.inflate(inflater, container, false)
        return binding.root

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
                this@SwimmingWorkouts.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        //For the following video startups, we send the youTubePlayerView to ensure the player becomes visible
        // TODO(See if there is a more effective way of doing it)

        //Code to start squats video
        val squatsYoutubeButton = view.findViewById<Button>(R.id.squatsPlayVideo)
        squatsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("iZTxa8NJH2g", youTubePlayerView)
        }

        //Code to start squatJumps video
        val squatJumpsYoutubeButton = view.findViewById<Button>(R.id.squatJumpsPlayVideo)
        squatJumpsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("Oju8PcNwUJM", youTubePlayerView)
        }

        //Code to start deadlifts video
        val deadliftsYoutubeButton = view.findViewById<Button>(R.id.deadliftsPlayVideo)
        deadliftsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("8np3vKDBJfc", youTubePlayerView)
        }

        //Code to start lunges video
        val lungesYoutubeButton = view.findViewById<Button>(R.id.lungesPlayVideo)
        lungesYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("IEB8cd1BfQU", youTubePlayerView)
        }

        //Code to start flutterKicks video
        val flutterKicksYoutubeButton = view.findViewById<Button>(R.id.flutterKicksPlayVideo)
        flutterKicksYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("0bSKm4jSLiA", youTubePlayerView)
        }

        //Code to start pullUps video
        val pullUpsYoutubeButton = view.findViewById<Button>(R.id.pullUpsPlayVideo)
        pullUpsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("ZPG8OsHKXLw", youTubePlayerView)
        }

        //Code to start skullCrushers video
        val skullCrushersYoutubeButton = view.findViewById<Button>(R.id.skullCrushersPlayVideo)
        skullCrushersYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("D1y1-sXZDA0", youTubePlayerView)
        }

        //Code to start planks video
        val planksYoutubeButton = view.findViewById<Button>(R.id.planksPlayVideo)
        planksYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("v25dawSzRTM", youTubePlayerView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}