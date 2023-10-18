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


    fun setAge(age: Number?) {
        _userAge.value = age
        Log.d("TwoWayBinding", "Age changed to: $age")
    }

    fun setWeight(weight: Number) {
        _userWeight.value = weight
        Log.d("TwoWayBinding", "Weight changed to: $weight")
    }
}
