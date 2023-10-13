package com.example.fitx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitx.databinding.LoginScreenBinding

class LoginScreen : Fragment() {

    private var _binding: LoginScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LoginScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.ButtonLogin.setOnClickListener {
           findNavController().navigate(R.id.action_LoginScreen_to_HomePage)
       }
        binding.createAccountTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreen_to_CreateAccount)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

