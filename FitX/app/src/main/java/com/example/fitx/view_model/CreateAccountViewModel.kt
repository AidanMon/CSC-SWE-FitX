package com.example.fitx.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitx.model.enums.ExperienceLevel
import com.example.fitx.model.enums.SportName
import com.example.fitx.model.User
import com.example.fitx.repository.UserRepository

/**
 * Create Account View Model binds to the Create Account xml to set null values for user input
 */
class CreateAccountViewModel : ViewModel() {

    val _userFirstName = MutableLiveData<String?>(null)
    val userFirstName: LiveData<String?> = _userFirstName

    private val _userLastName = MutableLiveData<String?>(null)
    val userLastName: LiveData<String?> = _userLastName

    private val _userEmail = MutableLiveData<String?>(null)
    val userEmail: LiveData<String?> = _userEmail

    private val _userAge = MutableLiveData<Number?>(null)
    var userAge: LiveData<Number?> = _userAge

    private val _userWeight = MutableLiveData<Number?>(null)
    val userWeight: LiveData<Number?> = _userWeight

    val sports: List<SportName> = SportName.values().toList()
    val levels: List<ExperienceLevel> = ExperienceLevel.values().toList()

    /**
     * Sets the age to a number value
     */
    fun setAge(age: Number?) {
        _userAge.value = age
        Log.d("TwoWayBinding", "Age changed to: $age")
    }

    /**
     * Sets the weight to a number value
     */
    fun setWeight(weight: Number) {
        _userWeight.value = weight
        Log.d("TwoWayBinding", "Weight changed to: $weight")
    }
}
