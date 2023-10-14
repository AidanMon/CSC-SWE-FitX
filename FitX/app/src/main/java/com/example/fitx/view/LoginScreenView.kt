package com.example.fitx.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.LoginScreenBinding
import com.example.fitx.view_model.CreateAccountViewModel
import com.example.fitx.view_model.LoginScreenViewModel
import kotlin.math.log

class LoginScreenView : Fragment() {
    private val loginScreenViewModel: LoginScreenViewModel by viewModels()
    private var _binding: LoginScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.login__screen, container, false)

        // Set the ViewModel for the binding
        _binding?.login = loginScreenViewModel

        // Specify the binding's lifecycle owner
        _binding?.lifecycleOwner = viewLifecycleOwner

        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginScreenViewModel.setUserName(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }

        })
        binding.userPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginScreenViewModel.setUserPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }

        })

       binding.ButtonLogin.setOnClickListener {
           findNavController().navigate(R.id.action_LoginScreen_to_HomePage)
           loginScreenViewModel.getLoginInfo()
       }
        binding.createAccountTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreen_to_CreateAccount)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("LoginScreenView","UserName: "+loginScreenViewModel.getLoginInfo().username)
        Log.d("LoginScreenView","Password: "+loginScreenViewModel.getLoginInfo().password)
        _binding = null
    }
}

