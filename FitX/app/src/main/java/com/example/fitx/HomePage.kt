package com.example.fitx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitx.databinding.HomePageBinding

class HomePage: Fragment() {
    private var _binding: HomePageBinding ? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_LoginScreen_to_HomePage)
        }

        */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}