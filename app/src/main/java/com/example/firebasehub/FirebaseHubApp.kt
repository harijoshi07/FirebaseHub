package com.example.firebasehub

import android.app.Application
import com.google.firebase.FirebaseApp

class FirebaseHubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}