package com.example.fitx.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.HomePageBinding
import com.example.fitx.repository.UserRepository

/**
 * Home Page holds all the data that is going to displayed to the user to interact with
 */
class HomePage: Fragment() {
    private var _binding: HomePageBinding ? = null
    private var userRepository = UserRepository()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // userRepository.getCurrentUserFullName(){ fullname ->
           // run {
                //binding.loginMessageTextView.text = "Welcome " + fullname + " to FitX You are logged in!"
            //}
       // }
        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Button to go from home to sport workouts page
        binding.toSports.setOnClickListener {
            findNavController().navigate((R.id.action_HomePage_to_SportsWorkouts))
        }

        //Button for user sport
        binding.userSport.setOnClickListener {
            //Code to find user sport and go to that page
            userRepository.getCurrentUser { user ->
                when (user.sportId.toInt()) {
                    0 -> findNavController().navigate(R.id.action_HomePage_to_BasketballWorkouts)
                    1 -> findNavController().navigate(R.id.action_HomePage_to_BaseballWorkouts)
                    2 -> findNavController().navigate(R.id.action_HomePage_to_SwimmingWorkouts)
                    3 -> findNavController().navigate(R.id.action_HomePage_to_SoccerWorkouts)
                    4 -> findNavController().navigate(R.id.action_HomePage_to_FootballWorkouts)
                    5 -> findNavController().navigate(R.id.action_HomePage_to_VolleyballWorkouts)
                    6 -> findNavController().navigate(R.id.action_HomePage_to_TennisWorkouts)
                }
            }


        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}