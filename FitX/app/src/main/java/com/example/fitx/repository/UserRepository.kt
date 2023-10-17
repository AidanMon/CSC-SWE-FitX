package com.example.fitx.repository
import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName
import com.example.fitx.model.User
import com.example.fitx.model.UserLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    fun getUser(): User {
        return User("", "", 0, 0, "", ExperienceLevel.Beginner, SportName.Tennis)
    }

    fun UserLoginInfo(): UserLogin {
        return UserLogin("AriKey1234", "1234")
    }

    fun Signin(email: String, password: String,callback: (Boolean) -> Unit){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            val isSignedIn = task.isSuccessful
            callback(isSignedIn)
        }
    }

    fun getSportID(sport:String, callback: (Number) -> Unit){
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("Sports").document(sport).get().addOnSuccessListener{documentSnapShot ->
            if(documentSnapShot.exists()) {
                val sportId = documentSnapShot.data
                for(num in sportId?.values!!)
                    callback(num as Number)
            }
        }
    }

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
                userMap["SportID"] = sport
                userMap["ExpLevel"] = experienceLevel

                firestore.collection("users")
                    .document(user?.uid ?: "")
                    .set(userMap)
           }
            callback(isRegistered)
        }
    }
    fun SignOut(){
     auth.signOut()
    }
}