package com.example.chat_app.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.R
import com.example.chat_app.dataBase.AppUser
import com.example.chat_app.dataBase.DataBaseUtils
import com.example.chat_app.dataBase.signInFireStore
import com.example.chat_app.home.HomeScreen
import com.example.chat_app.login.Login
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        checkUser()
    }

     fun checkUser() {
        val current = Firebase.auth.currentUser
        if (current == null) {
            startLoginActivity()
        } else {
            signInFireStore(userid = current.uid, onSuccessListener = {
                val user = it.toObject(AppUser::class.java)
                DataBaseUtils.user = user
                goToHomeActivity()
            }, onFailureListener = {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun goToHomeActivity() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)

    }
}