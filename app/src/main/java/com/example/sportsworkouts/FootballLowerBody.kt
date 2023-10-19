package com.example.sportsworkouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.sportsworkouts.databinding.FootballLowerBodyFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class FootballLowerBody : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: FootballLowerBodyFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FootballLowerBodyFragBinding.inflate(inflater, container, false)
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
                this@FootballLowerBody.youTubePlayer = youTubePlayer
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

        //Code to deadlift straight press video
        val deadliftStraightYoutubeButton = view.findViewById<Button>(R.id.deadliftStraightPlayVideo)
        deadliftStraightYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("q3mVYFBaLq4", youTubePlayerView)
        }

        //Code to deadlift conventional press video
        val deadliftConventionalYoutubeButton = view.findViewById<Button>(R.id.deadliftConventionalPlayVideo)
        deadliftConventionalYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("8np3vKDBJfc", youTubePlayerView)
        }

        //Code to start lying leg curls video
        val lyingLegCurlsYoutubeButton = view.findViewById<Button>(R.id.lyingLegCurlsPlayVideo)
        lyingLegCurlsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("C42VF9h5Ff0", youTubePlayerView)
        }

        //Code to start lunges video
        val lungesYoutubeButton = view.findViewById<Button>(R.id.lungesPlayVideo)
        lungesYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("IEB8cd1BfQU", youTubePlayerView)
        }

        //Code to start calf raises fly video
        val calfRaisesYoutubeButton = view.findViewById<Button>(R.id.calfRaisesPlayVideo)
        calfRaisesYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("OscsK8RMelQ", youTubePlayerView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}