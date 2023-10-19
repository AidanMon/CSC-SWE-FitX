package com.example.sportsworkouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sportsworkouts.databinding.FootballWorkoutsFragBinding

class FootballWorkouts : Fragment() {

    private var _binding: FootballWorkoutsFragBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FootballWorkoutsFragBinding.inflate(inflater, container, false)
        return binding.root

    }

    //Code that was use for the previous button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toFootballUpperBody.setOnClickListener {
            findNavController().navigate(R.id.action_FootballWorkouts_to_FootballUpperBody)
        }
        binding.toFootballLowerBody.setOnClickListener{
            findNavController().navigate((R.id.action_FootballWorkouts_to_FootballLowerBody))
        }
        binding.toFootballSpeedAndAgility.setOnClickListener{
            findNavController().navigate((R.id.action_FootballWorkouts_to_FootballSaA))
        }
        binding.toFootballFullBodyExplosiveStrength.setOnClickListener{
            findNavController().navigate((R.id.action_FootballWorkouts_to_FootballExplosiveStrength))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}