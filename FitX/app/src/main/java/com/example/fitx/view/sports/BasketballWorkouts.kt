package com.example.fitx.view.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.fitx.R
import com.example.fitx.databinding.BasketballWorkoutsFragBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class BasketballWorkouts : Fragment() {

    //Variables for the youtube player
    private lateinit var youTubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null

    private var isFullscreen = false

    private var _binding: BasketballWorkoutsFragBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BasketballWorkoutsFragBinding.inflate(inflater, container, false)
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
                this@BasketballWorkouts.youTubePlayer = youTubePlayer
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

        //Code to start lateralLunges video
        val lateralLungesYoutubeButton = view.findViewById<Button>(R.id.lateralLungesPlayVideo)
        lateralLungesYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("xHmHwpe68fc", youTubePlayerView)
        }

        //Code to start lateralBounds video
        val lateralBoundsYoutubeButton = view.findViewById<Button>(R.id.lateralBoundsPlayVideo)
        lateralBoundsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("ZkYORFHgRTw", youTubePlayerView)
        }

        //Code to start dumbbellBenchPress video
        val dumbbellBenchPressYoutubeButton = view.findViewById<Button>(R.id.dumbbellBenchPressPlayVideo)
        dumbbellBenchPressYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("SidmT09GXz8", youTubePlayerView)
        }

        //Code to start gobletSquats video
        val gobletSquatsYoutubeButton = view.findViewById<Button>(R.id.gobletSquatsPlayVideo)
        gobletSquatsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("7-80HiXX1K8", youTubePlayerView)
        }

        //Code to start pullUps video
        val pullUpsYoutubeButton = view.findViewById<Button>(R.id.pullUpsPlayVideo)
        pullUpsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("ZPG8OsHKXLw", youTubePlayerView)
        }

        //Code to start rows video
        val rowsYoutubeButton = view.findViewById<Button>(R.id.rowsPlayVideo)
        rowsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("Nqh7q3zDCoQ", youTubePlayerView)
        }

        //Code to start trapBarDeadlifts video
        val trapBarDeadliftsYoutubeButton = view.findViewById<Button>(R.id.trapBarDeadliftsPlayVideo)
        trapBarDeadliftsYoutubeButton.setOnClickListener {
            //TODO(getVideo ID from database)
            changeVideo("v-SrIcAp3vM", youTubePlayerView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView.release()
        _binding = null
    }
}