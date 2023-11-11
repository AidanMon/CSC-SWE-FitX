package com.example.fitx.repository
import com.example.fitx.model.SportTips
// This class is made to help test the repositary classes by simulating Firebase

//simulates authentication
interface AuthInterface {
    fun createUserWithEmailAndPassword(email: String, password: String, callback: (Boolean, String?) -> Unit)
}

//simulates firestore
interface FirestoreInterface {
    fun saveUserDetails(userId: String, userDetails: Map<String, Any>, callback: (Boolean) -> Unit)
    fun getSportId(sportName: String, callback: (Number?) -> Unit)

    fun getHealthyTips(sportId: Int, callback: (List<SportTips>) -> Unit)
}

//contains the functions that are tested in the test cases
class RepositoryTest(private val auth: AuthInterface, private val firestore: FirestoreInterface) {

    //simulating the dictionary in firestore
    private val sportDictionary = mapOf(1 to "Baseball", 2 to "Basketball", 3 to "Football", 4 to "Soccer", 5 to "Swimming", 6 to "Tennis", 7 to "Volleyball")


    fun getSportID(sport: String, callback: (Number) -> Unit) {
        firestore.getSportId(sport) { sportId ->
            if (sportId != null) {
                callback(sportId)
            }
        }
    }

    fun getHealthyTips(callback: (List<SportTips>) -> Unit) {
        val currentUserSportId = 123 // holder value

        firestore.getHealthyTips(currentUserSportId) { tips ->
            callback(tips) // tips is a List<SportTips>
        }
    }


    fun SignUp(email: String, password: String, fname:String, lname: String, age:Number, weight:Number, sport: String, experienceLevel: String, callback: (Boolean) -> Unit){

        auth.createUserWithEmailAndPassword(email, password) { isRegistered, userId ->
            if (isRegistered) {
                val userMap = HashMap<String, Any>()
                userMap["First Name"] = fname
                userMap["Last Name"] = lname
                userMap["Age"] = age
                userMap["Weight"] = weight
                userMap["ExpLevel"] = experienceLevel

                // Simulate getting Sport ID (this will need to be adapted to fit your mock logic)
                val sportId = 123 // Example sport ID
                userMap["Sport ID"] = sportId

                // Save user details (this replaces the direct Firestore call)
                firestore.saveUserDetails(userId ?: "", userMap) { success ->
                    // Handle the result of saving user details
                }
            }
            callback(isRegistered)
        }
    }


}

//helps mock the authentication process
class MockAuth(private val shouldSucceed: Boolean) : AuthInterface {
    override fun createUserWithEmailAndPassword(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        callback(shouldSucceed, if (shouldSucceed) "testUserId" else null)
    }
}

//helps mock the firestore database
class MockFirestore(private val shouldSucceed: Boolean, private val returnEmptyList: Boolean = false) : FirestoreInterface {
    override fun saveUserDetails(userId: String, userDetails: Map<String, Any>, callback: (Boolean) -> Unit) {
        callback(true)
    }

    override fun getSportId(sportName: String, callback: (Number?) -> Unit) {
        if (shouldSucceed) {
            val simulatedSportId = 1 // example ID for success
            callback(simulatedSportId)
        } else {
            callback(null) // simulates failure
        }
    }

    override fun getHealthyTips(sportId: Int, callback: (List<SportTips>) -> Unit) {
        if (!shouldSucceed) {
            callback(emptyList())
            return
        }

        if (returnEmptyList) {
            callback(emptyList())
        } else {
            val simulatedTips = listOf(SportTips("TipID", "Example Tip"))
            callback(simulatedTips)
        }
    }

}
