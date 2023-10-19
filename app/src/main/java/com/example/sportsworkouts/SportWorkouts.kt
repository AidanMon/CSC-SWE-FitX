package com.example.sportsworkouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sportsworkouts.databinding.SportWorkoutsFragBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SportWorkouts : Fragment() {

    private var _binding: SportWorkoutsFragBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SportWorkoutsFragBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toFootballWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_FootballWorkouts)
        }
        binding.toBaseballWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_BaseballWorkouts)
        }
        binding.toBasketballWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_BasketballWorkouts)
        }
        binding.toSoccerWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_SoccerWorkouts)
        }
        binding.toSwimmingWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_SwimmingWorkouts)
        }
        binding.toTennisWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_TennisWorkouts)
        }
        binding.toVolleyballWorkouts.setOnClickListener {
            findNavController().navigate(R.id.action_SportWorkouts_to_VolleyballWorkouts)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}