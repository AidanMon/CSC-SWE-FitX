package com.example.fitx

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fitx.databinding.CreateAccountBinding
import com.example.fitx.view_model.CreateAccountViewModel

class CreateAccountView: Fragment() {
    private val createAccountVM: CreateAccountViewModel by viewModels()
    private var _binding: CreateAccountBinding? = null

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

        createAccountVM.getUserDataFromRepository()
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createAccountVM.setUserFirstName(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }

        })
        binding.userLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createAccountVM.setUserLastName(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }
        })
        binding.userEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createAccountVM.setUserEmail(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed
            }
        })

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


            createAccountVM.selectedExperienceLevelPosition.observe(viewLifecycleOwner) { _ ->
                createAccountVM.updateSelectedExperienceLevel()
            }

        createAccountVM.selectedSportNamePosition.observe(viewLifecycleOwner) { _ ->
            createAccountVM.updateSelectedSportName()
        }



        /*binding.loginButton.setOnClickListener {
             findNavController().navigate(R.id.action_LoginScreen_to_HomePage)
         }
         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("CreateAccountView","First Name: "+createAccountVM.createUserWithUpdatedData().firstName)
        Log.d("CreateAccountView","Last Name: "+createAccountVM.createUserWithUpdatedData().lastName)
        Log.d("CreateAccountView","Email Address: "+createAccountVM.createUserWithUpdatedData().email)
        Log.d("CreateAccountView","Age: "+createAccountVM.createUserWithUpdatedData().age)
        Log.d("CreateAccountView","weight: "+createAccountVM.createUserWithUpdatedData().weight)
        Log.d("CreateAccountView","Experience Level: "+createAccountVM.createUserWithUpdatedData().experienceLevel.toString())
        Log.d("CreateAccountView","Sport: "+createAccountVM.createUserWithUpdatedData().sport.toString())
        _binding = null
    }
}