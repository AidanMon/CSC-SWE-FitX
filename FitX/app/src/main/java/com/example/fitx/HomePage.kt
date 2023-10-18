package com.example.fitx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitx.databinding.HomePageBinding
import com.example.fitx.repository.UserRepository

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

        userRepository.getCurrentUserFullName(){ fullname ->
            run {
                binding.loginMessageTextView.text = "Welcome " + fullname + " to FitX You are logged in!"
            }
        }
        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.logoutButton.setOnClickListener {
           userRepository.SignOut()
           Toast.makeText(requireActivity(), "Logged Out", Toast.LENGTH_SHORT).show()
           findNavController().navigate(R.id.action_HomePage_to_LoginScreen)
               }
           }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}