package com.example.fitx.repository

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessageService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM Token", token)
        // Handle the new FCM token here, e.g., send it to your server
        // or store it locally for later use.
    }

    fun getFCMToken(callback: (String) -> Unit){
        FirebaseMessaging.getInstance().token.addOnCompleteListener{task ->
            if(!task.isSuccessful){
                Log.w("First IF", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            callback(task.result)
            // Log.d("Token", token)
        }
    }
}