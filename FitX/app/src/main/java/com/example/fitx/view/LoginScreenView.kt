package com.example.fitx.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.LoginScreenBinding
import com.example.fitx.repository.UserRepository
import com.example.fitx.view_model.LoginScreenViewModel

/**
 * Login Screen View holds all the text change and button click events to move to other views for send
 * data to the firebase database
 */
class LoginScreenView : Fragment() {
    private val loginScreenViewModel: LoginScreenViewModel by viewModels()
    private var _binding: LoginScreenBinding? = null
    private val userRepository = UserRepository()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.login_screen, container, false)

        // Set the ViewModel for the binding
        _binding?.login = loginScreenViewModel

        // Specify the binding's lifecycle owner
        _binding?.lifecycleOwner = viewLifecycleOwner

        return _binding?.root
    }

    /**
     * onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Login button Lister, checks if all conditions are meet before trying to login into the app
         */
       binding.ButtonLogin.setOnClickListener {
           if(binding.userName.text.toString().trim().isEmpty()){
               Toast.makeText(requireActivity(), "Email Required", Toast.LENGTH_LONG).show()
           }
           else if(binding.userPassword.text.toString().trim().isEmpty()){
               Toast.makeText(requireActivity(), "Password Required", Toast.LENGTH_LONG).show()
           }
           else{
               userRepository.Signin(binding.userName.text.toString(),binding.userPassword.text.toString()){isSuccessful ->
                   if(isSuccessful){
                       findNavController().navigate(R.id.action_LoginScreen_to_HomePage)
                   }
                   else{
                       Toast.makeText(requireActivity(), "Authentication Failed Try again", Toast.LENGTH_LONG).show()
                   }
               }
           }

       }
        /**
         * Registration button lister to navigate to the Create Account View
         */
        binding.createAccountTextView.setOnClickListener {
            findNavController().navigate(R.id.action_LoginScreen_to_CreateAccount)
        }
    }

    /**
     * onDestroyView
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

