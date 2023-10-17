package com.example.chasefitx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class LoginScreen : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        // Initialize UI components
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registerButton: Button = findViewById(R.id.registerButton)

        auth = FirebaseAuth.getInstance()

        // Set click listeners
        loginButton.setOnClickListener {
            // Handle login action
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform authentication and navigate to the next screen if successful
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login is successful
                        val user = auth.currentUser
                        // Navigate to the next screen (e.g., HomeActivity)
                        Toast.makeText(this, "Accepted", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle failed login
                        Toast.makeText(this, "Login failed: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }

        }

        registerButton.setOnClickListener {
            // Navigate to the registration screen (e.g., RegisterActivity)
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun performLogin(email: String, password: String): Boolean {
        // Implement your authentication logic here (e.g., Firebase Authentication)
        // Return true if login is successful, otherwise, return false
        // You can replace this with your actual authentication logic
        return true
    }

}