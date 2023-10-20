package com.example.fitx.view.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.fitx.R
import com.example.fitx.databinding.FootballSpeedAndAgilityFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class FootballSaA : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false

    private var _binding: FootballSpeedAndAgilityFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FootballSpeedAndAgilityFragBinding.inflate(inflater, container, false)
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
        //TODO(Add fullscreen option to youtube player)!!!!!!!!!!!!!!!!!!!!!!!!
        youTubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(player: YouTubePlayer) {
                this@FootballSaA.youTubePlayer = youTubePlayer
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

        //Code to start 10m sprints video
        val sprints10mYoutubeButton = view.findViewById<Button>(R.id.sprints10mPlayVideo)
        sprints10mYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("ojb9bf1-lhk", youTubePlayerView)
        }

        //Code to 25m sprints video
        val sprints25mYoutubeButton = view.findViewById<Button>(R.id.sprints25mPlayVideo)
        sprints25mYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("ojb9bf1-lhk", youTubePlayerView)
        }

        //Code to 400m sprint video
        val sprint400mYoutubeButton = view.findViewById<Button>(R.id.sprint400mPlayVideo)
        sprint400mYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("ojb9bf1-lhk", youTubePlayerView)
        }

        //Code to start suicides video
        val suicidesYoutubeButton = view.findViewById<Button>(R.id.suicidesPlayVideo)
        suicidesYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("qt5TxeB11LI", youTubePlayerView)
        }

        //Code to start jump and reach fly video
        val jumpAndReachYoutubeButton = view.findViewById<Button>(R.id.jumpAndReachPlayVideo)
        jumpAndReachYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("Oju8PcNwUJM", youTubePlayerView)
        }

        //Code to start agility ladder video
        val agilityLadderYoutubeButton = view.findViewById<Button>(R.id.agilityLadderPlayVideo)
        agilityLadderYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("miV1LdY01Ng", youTubePlayerView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}