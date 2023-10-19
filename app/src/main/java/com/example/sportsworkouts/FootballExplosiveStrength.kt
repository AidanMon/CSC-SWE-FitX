package com.example.sportsworkouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.sportsworkouts.databinding.FootballExplosiveStrengthFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class FootballExplosiveStrength: Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false


    private var _binding: FootballExplosiveStrengthFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FootballExplosiveStrengthFragBinding.inflate(inflater, container, false)
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
                this@FootballExplosiveStrength.youTubePlayer = youTubePlayer
                youTubePlayer?.loadVideo("S0Q4gqBUs7c", 0f)

                val enterFullscreenButton = view.findViewById<Button>(R.id.enterFullScreenButton)
                enterFullscreenButton.setOnClickListener {
                    youTubePlayer?.toggleFullscreen()
                }
                youTubePlayer = player
            }
        })

        //For the following video startups, we send the youTubePlayerView to ensure the player becomes visible

        //Code to power cleans sprints video
        val powerCleansYoutubeButton = view.findViewById<Button>(R.id.powerCleansPlayVideo)
        powerCleansYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("iPDJSJIiFBU", youTubePlayerView)
        }

        //Code to box jumps video
        val boxJumpsYoutubeButton = view.findViewById<Button>(R.id.boxJumpsPlayVideo)
        boxJumpsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("Z9Vw6MxOHP8", youTubePlayerView)
        }

        //Code to snatches video
        val snatchesYoutubeButton = view.findViewById<Button>(R.id.snatchesPlayVideo)
        snatchesYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("42gl2a-01Rc", youTubePlayerView)
        }

        //Code to start medicine ball throw situp fly video
        val medicineBallThrowSitupsYoutubeButton = view.findViewById<Button>(R.id.medicineBallThrowSitupsPlayVideo)
        medicineBallThrowSitupsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("C_VZu8IO5xg", youTubePlayerView)
        }

        //Code to start pushup to box jump video
        val pushupToBoxJumpYoutubeButton = view.findViewById<Button>(R.id.pushupToBoxJumpPlayVideo)
        pushupToBoxJumpYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("vyBpwTlv3dg", youTubePlayerView)
        }

        //Code to start inclined weighted situps video
        val inclineWeightedSitupsYoutubeButton = view.findViewById<Button>(R.id.inclineWeightedSitupsPlayVideo)
        inclineWeightedSitupsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("hNoqupytA8g", youTubePlayerView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}