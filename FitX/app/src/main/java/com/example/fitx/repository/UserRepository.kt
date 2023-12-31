package com.example.fitx.repository
import android.util.Log
import com.example.fitx.model.User
import com.example.fitx.model.enums.SportName
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * User Repository hold all operations that will be done by the user or retrieving user information
 */
class UserRepository {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var messageService = MessageService()

    /**
    Gets the users full name from the firebase users collection
     */
    fun getCurrentUser(callback: (User) -> Unit) {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        firestore = FirebaseFirestore.getInstance()
        user?.uid?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { documentSnapShot ->
                    if (documentSnapShot.exists()) {
                        val userData = documentSnapShot.data
                        callback(User(
                            firstName = userData?.get("First Name").toString(),
                            lastName = userData?.get("Last Name").toString(),
                            age = userData?.get("Age") as Number,
                            weight = userData?.get("Weight") as Number,
                            "",
                            experienceLevel = userData?.get("ExpLevel").toString(),
                            sportId = userData?.get("Sport ID") as Number)
                        )
                    }
                }
        }
    }

    /**
    When user logs into the application, it will authenticate using firebase to see if there account exist
     */
    fun Signin(email: String, password: String,callback: (Boolean) -> Unit){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            val isSignedIn = task.isSuccessful
            callback(isSignedIn)
        }
    }

    /**
    When user is registering on the app, it will send there info to the users table in firebase
     */
    fun SignUp(email: String, password: String, fname:String, lname: String, age:Number, weight:Number, sport: String, experienceLevel: String, callback: (Boolean) -> Unit){
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() {task ->
            val isRegistered = task.isSuccessful
            if(isRegistered) {
                val user = auth.currentUser
                val userMap = HashMap<String, Any>()
                userMap["First Name"] = fname
                userMap["Last Name"] = lname
                userMap["Age"] = age
                userMap["Weight"] = weight
                userMap["ExpLevel"] = experienceLevel
                userMap["Sport ID"] = SportName.valueOf(sport).ordinal
                messageService.getFCMToken{ token ->
                    userMap["Token"] = token
                    firestore.collection("users")
                        .document(user?.uid ?: "")
                        .set(userMap)
                    }
                }
            callback(isRegistered)
        }
    }

    /**
     * When the user provides feedback on the application. It will save the response in the database.
     */
    fun retrieveFeedback(message: String){
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser?.uid
        if (user != null) {
            val messageMap = HashMap<String, Any>()
            messageMap["Message"] = message
            firestore.collection("users").
            document(user).collection("FeedBack").
            document().set(messageMap)
        }
    }
    /**
    When user presses the Logout button it will sign them out of the app
     */
    fun SignOut(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
    }
}