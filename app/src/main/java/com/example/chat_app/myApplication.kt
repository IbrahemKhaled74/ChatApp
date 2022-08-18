package com.example.chat_app

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class myApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}