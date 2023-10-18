package com.example.fitx.repository
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    fun getCurrentUserFullName(callback: (String) -> Unit) {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        firestore = FirebaseFirestore.getInstance()
        user?.uid?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { documentSnapShot ->
                    if (documentSnapShot.exists()) {
                        val userData = documentSnapShot.data
                        val firstName = userData?.get("First Name");
                        val lastName = userData?.get("Last Name")
                        val fullName = firstName.toString() + " " + lastName.toString()
                        callback(fullName)
                    }
                }
        }
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
                Log.d("Hashmap",userMap.toString())
                userMap["ExpLevel"] = experienceLevel
                getSportID(sport) {
                    Log.d("SportID", it.toString())
                    userMap["Sport ID"] = it.toInt()

                    firestore.collection("users")
                        .document(user?.uid ?: "")
                        .set(userMap)
                }

           }
            callback(isRegistered)
        }
    }
    fun SignOut(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
    }
}