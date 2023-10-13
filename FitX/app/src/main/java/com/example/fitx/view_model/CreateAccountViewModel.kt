package com.example.fitx.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitx.ExperienceLevel
import com.example.fitx.SportName
import com.example.fitx.User
import com.example.fitx.repository.UserRepository

class CreateAccountViewModel : ViewModel() {
    // var userFirstName = MutableLiveData<String>()

    private val userRepository = UserRepository()
    private val _userData = MutableLiveData<User?>(null)

    val userData: LiveData<User?> = _userData

    private val _userFirstName = MutableLiveData<String?>(null)
    val userFirstName: LiveData<String?> = _userFirstName

    private val _userLastName = MutableLiveData<String?>(null)
    val userLastName: LiveData<String?> = _userLastName

    private val _userEmail = MutableLiveData<String?>(null)
    val userEmail: LiveData<String?> = _userEmail

    private val _userAge = MutableLiveData<Number?>(null)
    val userAge: LiveData<Number?> = _userAge

    private val _userWeight = MutableLiveData<Number?>(null)
    val userWeight: LiveData<Number?> = _userWeight


    val sports: List<SportName> = SportName.values().toList()
    val levels: List<ExperienceLevel> = ExperienceLevel.values().toList()
    val selectedSport = MutableLiveData<SportName>()
    val selectedExperienceLevel = MutableLiveData<ExperienceLevel>()
    val selectedExperienceLevelPosition = MutableLiveData<Int>(2)
    val selectedSportNamePosition = MutableLiveData<Int>(3)

    fun getCurrentUserData(){
       setUser(User(
            "Test First Name",
            "Test Last Name",
            12,
            12,
            "Test Email",
            ExperienceLevel.Expert,
            SportName.Soccor
        ))
    }

    fun setUser(user: User) {
        _userData.value = user
    }

    fun getUserDataFromRepository(){
        val cUser = userRepository.getUser()
        setUser(cUser)
    }

    fun setUserFirstName(firstName: String) {
        /*
        val currentUserData = _userData.value
        currentUserData?.firstName = firstName
        _userData.value = currentUserData
         */
        _userFirstName.value = firstName
        Log.d("TwoWayBinding", "First Name changed to: $firstName")
    }

    fun setUserLastName(lastName: String) {
        val currentUserData = _userData.value
        currentUserData?.lastName = lastName
        _userData.value = currentUserData
        Log.d("TwoWayBinding", "Last Name changed to: $lastName")
    }

    fun setUserEmail(email: String) {
        val currentUserData = _userData.value
        currentUserData?.email = email
        _userData.value = currentUserData
        Log.d("TwoWayBinding", "Email changed to: $email")
    }

    fun setAge(age: Number) {
        val currentUserData = _userData.value
        currentUserData?.age = age
        _userData.value = currentUserData
        Log.d("TwoWayBinding", "Age changed to: $age")
    }

    fun setWeight(weight: Number) {
        val currentUserData = _userData.value
        currentUserData?.weight = weight
        _userData.value = currentUserData
        Log.d("TwoWayBinding", "Weight changed to: $weight")
    }

    fun createUserWithUpdatedData(): User {
        val currentUserData = _userData.value ?: User("","",0,0,"",ExperienceLevel.Expert, SportName.Soccor) // Use a default user if the current data is null
        var updatedFirstName = currentUserData.firstName
        var updatedLastName = currentUserData.lastName
        var updatedEmail = currentUserData.email
        var updateAge = currentUserData.age
        var updateWeight = currentUserData.weight
        var updateExperienceLevel = currentUserData.experienceLevel
        var updateSportName = currentUserData.sport

        // Use the values from your LiveData properties, if they are not null
        userFirstName.value?.let { updatedFirstName = it }
        userLastName.value?.let { updatedLastName = it }
        userEmail.value?.let { updatedEmail = it }
        userAge.value?.let { updateAge = it }
        userWeight.value?.let { updateWeight = it }
        selectedExperienceLevel.value?.let { updateExperienceLevel = it }
        selectedSport.value?.let {updateSportName = it}

        // Create a new User object with the updated data
        return User(updatedFirstName, updatedLastName,updateAge, updateWeight, updatedEmail,updateExperienceLevel,updateSportName)
    }

    fun updateSelectedSportName(){
        selectedSportNamePosition.value?.let {position ->
            if (position >= 0 && position < sports.size){
                selectedSport.value = sports[position]
            }
            Log.d("TwoWayBinding", "Sport changed to: ${selectedSport.value}")
        }
    }

    fun updateSelectedExperienceLevel() {
        selectedExperienceLevelPosition.value?.let { position ->
            if (position >= 0 && position < levels.size) {
                selectedExperienceLevel.value = levels[position]
            }
            Log.d("TwoWayBinding", "Experience Level changed to: ${selectedExperienceLevel.value}")
        }
    }
}
