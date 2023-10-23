package com.example.fitx.view.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.fitx.R
import com.example.fitx.databinding.FootballUpperBodyFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class FootballUpperBody : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: FootballUpperBodyFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FootballUpperBodyFragBinding.inflate(inflater, container, false)
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
                this@FootballUpperBody.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        //For the following video startups, we send the youTubePlayerView to ensure the player becomes visible
        //TODO(See if there is a more effective way of doing it)

        //Code to start bench press video
        val benchYoutubeButton = view.findViewById<Button>(R.id.benchPressPlayVideo)
        benchYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("0cXAp6WhSj4", youTubePlayerView)
        }

        //Code to start incline dumbbell bench press video
        val inclineDumbbellYoutubeButton = view.findViewById<Button>(R.id.inclineDumbbellPlayVideo)
        inclineDumbbellYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("oCEEgEQLG8o", youTubePlayerView)
        }

        //Code to start shoulder press video
        val shoulderPressYoutubeButton = view.findViewById<Button>(R.id.shoulderPressPlayVideo)
        shoulderPressYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("bQPhGIJJ-jQ", youTubePlayerView)
        }

        //Code to start shrugs video
        val shrugsYoutubeButton = view.findViewById<Button>(R.id.shrugsPlayVideo)
        shrugsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("R3FLhX81MYg", youTubePlayerView)
        }

        //Code to start cable crossover video
        val cableCrossoverYoutubeButton = view.findViewById<Button>(R.id.cableCrossoverPlayVideo)
        cableCrossoverYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("tGXIQR89-JE", youTubePlayerView)
        }

        //Code to start dumbbell fly video
        val dumbbellFlyYoutubeButton = view.findViewById<Button>(R.id.dumbbellFlyPlayVideo)
        dumbbellFlyYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("Jz7oEmzhnfE", youTubePlayerView)
        }

        //Code to start alternating dumbbell curls video
        val alternatingDumbbellCurlsYoutubeButton = view.findViewById<Button>(R.id.alternatingDumbbellCurlsPlayVideo)
        alternatingDumbbellCurlsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("FHY_2t7R714", youTubePlayerView)
        }

        //Code to start tricep dips video
        val tricepDipsYoutubeButton = view.findViewById<Button>(R.id.tricepDipsPlayVideo)
        tricepDipsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("36krJZYK_dU", youTubePlayerView)
        }

        //Code to start skull crushers video
        val skullCrushersYoutubeButton = view.findViewById<Button>(R.id.skullCrushersPlayVideo)
        skullCrushersYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("D1y1-sXZDA0", youTubePlayerView)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}