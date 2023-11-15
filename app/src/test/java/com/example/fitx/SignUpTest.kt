package com.example.fitx

import com.example.fitx.repository.MockAuth
import com.example.fitx.repository.MockFirestore
import com.example.fitx.repository.RepositoryTest
import org.junit.Test

class SignUpTest {

    @Test
    fun testSuccessfulSignUp() {

        val mockAuth = MockAuth(true) // Simulates successful authentication
        val mockFirestore = MockFirestore(true) // Simulates successful data saving
        val userRepository = RepositoryTest(mockAuth, mockFirestore)


        userRepository.SignUp("test@example.com", "password123", "John", "Doe", 25, 70, "Tennis", "Beginner") { isSuccess ->
            assert(isSuccess) // Expect success
        }
    }

    // Test for failed sign-up due to invalid email
    @Test
    fun testFailedSignUpInvalidEmail() {

        val mockAuth = MockAuth(false) // Simulates failure in authentication
        val mockFirestore = MockFirestore(true)
        val userRepository = RepositoryTest(mockAuth, mockFirestore)

        userRepository.SignUp("invalid_email", "password123", "Jane", "Smith", 30, 65, "Soccer", "Intermediate") { isSuccess ->
            assert(!isSuccess) // Expect failure
        }
    }

    // Test for failed sign-up due to invalid password
    @Test
    fun testFailedSignUpInvalidPassword() {

        val mockAuth = MockAuth(false) // Simulates failure in authentication
        val mockFirestore = MockFirestore(true)
        val userRepository = RepositoryTest(mockAuth, mockFirestore)


        userRepository.SignUp("test@example.com", "weak", "Jane", "Smith", 30, 65, "Soccer", "Intermediate") { isSuccess ->
            assert(!isSuccess) // Expect failure
        }
    }
}
