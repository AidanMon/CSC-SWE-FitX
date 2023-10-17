package com.example.chasefitx

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chasefitx.databinding.ActivityRegisterBinding
import android.widget.Spinner
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.EditText
import android.widget.Button
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var fNameEditText: EditText
    private lateinit var lNameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // Create arrays for sports and experience levels
        val sportsArray = arrayOf("Football", "Soccer", "Volleyball", "Basketball", "Baseball", "Tennis", "Swimming")
        val experienceLevelsArray = arrayOf("Beginner", "Intermediate", "Expert")

        // Initialize the Spinners
        val sportSpinner: Spinner = findViewById(R.id.sportSpinner)
        val experienceLevelSpinner: Spinner = findViewById(R.id.experienceLevelSpinner)

        // Create ArrayAdapter objects for sports and experience levels
        val sportAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sportsArray)
        val experienceLevelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, experienceLevelsArray)

        // Set the drop-down view resource
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        experienceLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapters for the Spinners
        sportSpinner.adapter = sportAdapter
        experienceLevelSpinner.adapter = experienceLevelAdapter



        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        fNameEditText = findViewById(R.id.firstNameEditText)
        lNameEditText = findViewById(R.id.lastNameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        weightEditText = findViewById(R.id.weightEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)


        //Authentication
        val firestore = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        //what happens after click
        registerButton.setOnClickListener {

            //Takes value from device and enters it into a variable
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val fname = fNameEditText.text.toString().trim()
            val lname = lNameEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()
            val weight = weightEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password are required.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registration successfull
                        val user = auth.currentUser
                        // Additional user information
                        val userMap = HashMap<String, Any>()
                        userMap["firstName"] = fname
                        userMap["lastName"] = lname
                        userMap["age"] = age
                        userMap["weight"] = weight
                        userMap["sport"] = sportSpinner.selectedItem.toString()
                        userMap["experienceLevel"] = experienceLevelSpinner.selectedItem.toString()


                        // Store the additional information in Firestore
                        firestore.collection("users")
                            .document(user?.uid ?: "")
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Registration failed. Error: " + e.message, Toast.LENGTH_SHORT).show()
                            }

                        // You can navigate to the next screen or perform other actions here
                    } else {
                        // Registration failed
                        val exception = task.exception
                        // Handle the error, e.g., display an error message to the user
                        Toast.makeText(this, "Registration failed. Error: " + exception?.message, Toast.LENGTH_LONG).show()
                        Log.e("Registration", "Registration failed", exception)

                    }
                }
        }
    }
}
