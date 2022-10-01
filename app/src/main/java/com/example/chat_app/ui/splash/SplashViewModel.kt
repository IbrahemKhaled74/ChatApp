package com.example.chat_app.ui.splash

import com.example.chat_app.base.baseViewModel
import com.example.domain.models.AppUser
import com.example.domain.models.DataBaseUtils
import com.example.domain.repos.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject constructor(val userRepository: UserRepository) : baseViewModel<Navigator>() {
    suspend fun checkUser() {
        val current = Firebase.auth.currentUser
        if (current == null) {
            navigator?.startLoginActivity()
        } else {
            try {
                userRepository.getUserFromFireBase(current.uid)
                val user =
                    userRepository.getUserFromFireBase(current.uid)
                        .toObject(AppUser::class.java)
                DataBaseUtils.user = user
                if (user != null)
                    navigator?.goToHomeActivity()
                else
                    navigator?.startLoginActivity()

            } catch (ex: Exception) {
                toastMessage.value = ex.localizedMessage
            }
        }
    }

}