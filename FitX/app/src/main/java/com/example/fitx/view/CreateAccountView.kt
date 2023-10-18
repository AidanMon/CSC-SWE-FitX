package com.example.fitx.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fitx.R
import com.example.fitx.databinding.CreateAccountBinding
import com.example.fitx.view_model.CreateAccountViewModel
import androidx.navigation.fragment.findNavController
import com.example.fitx.repository.UserRepository

class CreateAccountView: Fragment() {
    private val createAccountVM: CreateAccountViewModel by viewModels()
    private var _binding: CreateAccountBinding? = null
    private var userRepository = UserRepository()

    // This property is only valid between onCreateView and
    // onDestroyView.
     private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // createAccountVM = ViewModelProvider(this)[CreateAccountVM::class.java]
        _binding = DataBindingUtil.inflate(inflater, R.layout.create_account, container, false)

        // Set the ViewModel for the binding
        _binding?.user = createAccountVM

        // Specify the binding's lifecycle owner
        _binding?.lifecycleOwner = viewLifecycleOwner

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.toIntOrNull()?.let {
                    createAccountVM.setAge(it)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }
        })

        binding.userWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.toIntOrNull()?.let {
                    createAccountVM.setWeight(it)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }
        })

        binding.signupbutton.setOnClickListener() {
            if(binding.userPassword.text.length < 8){
                Toast.makeText(requireActivity(), "Password must be 8 characters", Toast.LENGTH_LONG).show()
            }else{
                createAccountVM.userWeight.value?.let { it1 ->
                    createAccountVM.userAge.value?.let { it2 ->
                        userRepository.SignUp(binding.userEmail.text.toString(),
                            binding.userPassword.text.toString(),
                            binding.userFirstName.text.toString(),
                            binding.userLastName.text.toString(),
                            it2,
                            it1,
                            binding.sportlist.selectedItem.toString(),
                            binding.experiencelevellist.selectedItem.toString()){ isSuccessful ->
                            if(isSuccessful){
                                Toast.makeText(requireActivity(), "You are Registered!", Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(requireActivity(), "Failed to Register", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
         }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}