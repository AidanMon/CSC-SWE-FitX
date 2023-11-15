package com.example.fitx.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.fitx.databinding.CreateAccountBinding
import com.example.fitx.model.SportTips
import com.example.fitx.model.User
import com.example.fitx.model.enums.SportName
import com.google.firebase.firestore.FirebaseFirestore

class TipsRepository {

    private val sportDictionary = mapOf(1 to "Baseball", 0 to "Basketball", 4 to "Football", 3 to "Soccer", 2 to "Swimming", 6 to "Tennis", 5 to "Volleyball" )
    private var userRepository = UserRepository()
    private lateinit var firestore: FirebaseFirestore

    @SuppressLint("SuspiciousIndentation")
    fun getHealthyTips(callback: (SportTips) -> Unit) {
        firestore = FirebaseFirestore.getInstance()
            userRepository.getCurrentUser { user ->
                    firestore.collection("Sports") .document(sportDictionary[user.sportId.toInt()].toString())
                        .collection("Healthy-Tips").get()
                        .addOnSuccessListener { names ->
                            for (name in names){
                                callback(SportTips(name.id, name.get("Tip").toString()))
                            }
                        }
                }
            }
            }

