package com.example.fitx.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName
import com.example.fitx.model.User
import com.example.fitx.repository.UserRepository

class CreateAccountViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _userData = MutableLiveData<User?>(null)

    val userData: LiveData<User?> = _userData

    val _userFirstName = MutableLiveData<String?>(null)
    val userFirstName: LiveData<String?> = _userFirstName

    private val _userLastName = MutableLiveData<String?>(null)
    val userLastName: LiveData<String?> = _userLastName

    private val _userEmail = MutableLiveData<String?>(null)
    val userEmail: LiveData<String?> = _userEmail

    private val _userAge = MutableLiveData<Number?>()
    var userAge: LiveData<Number?> = _userAge

    private val _userWeight = MutableLiveData<Number?>()
    val userWeight: LiveData<Number?> = _userWeight

    val sports: List<SportName> = SportName.values().toList()
    val levels: List<ExperienceLevel> = ExperienceLevel.values().toList()
    val selectedSport = MutableLiveData<SportName>()
    val selectedExperienceLevel = MutableLiveData<ExperienceLevel>()
    val selectedExperienceLevelPosition = MutableLiveData<Int>(2)
    val selectedSportNamePosition = MutableLiveData<Int>(3)
    val userAgeString: String = ""
    val userWeightString: String = ""

    /*
    fun setUser(user: User) {
        _userData.value = user
    }

    fun getUserDataFromRepository(){
        val cUser = userRepository.getUser()
        setUser(cUser)
    }
     */

    fun setUserFirstName(firstName: String) {
        _userFirstName.value = firstName
        Log.d("TwoWayBinding", "First Name changed to: $firstName")
    }

    fun setUserLastName(lastName: String) {
        _userLastName.value = lastName
        Log.d("TwoWayBinding", "Last Name changed to: $lastName")
    }

    fun setUserEmail(email: String) {
        _userEmail.value = email
        Log.d("TwoWayBinding", "Email changed to: $email")
    }

    fun setAge(age: Number?) {
        _userAge.value = age
        Log.d("TwoWayBinding", "Age changed to: $age")
    }


    fun setWeight(weight: Number) {
        _userWeight.value = weight

        Log.d("TwoWayBinding", "Weight changed to: $weight")
    }

    fun createUserWithUpdatedData(): User {
        val currentUserData = _userData.value ?: User("","",0,0,"",
            ExperienceLevel.Advanced, SportName.Soccer) // Use a default user if the current data is null
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
