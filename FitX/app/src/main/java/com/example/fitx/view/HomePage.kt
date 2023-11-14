package com.example.fitx.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.HomePageBinding
import com.example.fitx.model.Exercise
import com.example.fitx.repository.AllExerciseLists
import com.example.fitx.repository.ExerciseRepository
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

        //Universal exercise list
        val exerciseRepo = ExerciseRepository()
        var allExercises = mutableListOf<Exercise>()
        exerciseRepo.getAllExercises(object : ExerciseRepository.ExerciseCallback {
            override fun onExercisesReceived(exerciseList: MutableList<Exercise>) {
                // Handle the list of exercises
                allExercises = mutableListOf()          //Setting the list to empty so it doesn't cause issues by adding multiple copies of every exercise
                allExercises = exerciseList
                AllExerciseLists.exerciseList = allExercises

            }

            override fun onError(exception: MutableList<Exercise>) {
                // Handle errors
                allExercises = exception

            }
        })

        // User workout lists
        var allUserWorkouts = mutableListOf<Pair<String, MutableList<Exercise>>>()
        exerciseRepo.getUserWorkouts(object : ExerciseRepository.UserWorkoutsCallback {
            override fun onWorkoutsReceived(workoutTuples: MutableList<Pair<String, MutableList<Exercise>>>) {
                // Clear the existing list and add the new data
                allUserWorkouts.clear()
                allUserWorkouts.addAll(workoutTuples)
                AllExerciseLists.userMadeWorkouts = allUserWorkouts
            }

            override fun onError(exception: MutableList<Pair<String, MutableList<Exercise>>>) {
                // Handle errors
                allUserWorkouts.clear()
                allUserWorkouts.addAll(exception)
                AllExerciseLists.userMadeWorkouts = allUserWorkouts
            }
        })



        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Button to go from home to sport workouts page
        binding.toSports.setOnClickListener {
            findNavController().navigate((R.id.action_HomePage_to_SportsWorkouts))
        }

        binding.AllExercises.setOnClickListener {
            findNavController().navigate((R.id.action_HomePage_to_AllExercises))
        }

        binding.createAWorkoutButton.setOnClickListener {
            findNavController().navigate((R.id.action_HomePage_to_CreateAWorkout))
        }

        binding.userWorkoutsButton.setOnClickListener {
            findNavController().navigate((R.id.action_HomePage_to_UserWorkouts))
        }
        binding.inputUserWorkoutDataButton.setOnClickListener {
            findNavController().navigate((R.id.action_HomePage_to_InputUserWorkoutData))
        }
        binding.HealthyTips.setOnClickListener{
            findNavController().navigate(R.id.action_HomePage_to_HealthyTips)
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