package com.example.chat_app.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.chat_app.base.baseViewModel
import com.example.domain.models.AppUser
import com.example.domain.models.DataBaseUtils
import com.example.domain.repos.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(val userRepository: UserRepository) :
    baseViewModel<Navigator>() {
    var email = ObservableField<String>()
    var emailError = ObservableField<String>()
    var password = ObservableField<String>()
    var passwordError = ObservableField<String>()

    fun logIn() {
        if (validate()) {
            viewModelScope.launch {
                signInFireBase()
            }
        }
    }

    fun createNewAccount() {
        navigator?.openRegesterationScreen()

    }

    private suspend fun signInFireBase() {
        loading.value = true
        try {

            val task = userRepository.signInFireBase(email.get()!!, password.get()!!)
            getUserFromFireStore(task.user?.uid)
            loading.value = false


//          val task=auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
//                .await()
        } catch (ex: Exception) {
            loading.value = false
            Log.e("Not Done", ex.localizedMessage.toString())
            messageLiveData.value = ex.localizedMessage

        }
    }


    private suspend fun getUserFromFireStore(uid: String?) {
        try {
            val user = userRepository.getUserFromFireBase(uid!!)
                .toObject(AppUser::class.java)
            if (user == null) {
                messageLiveData.value = "Invalid Email Or Password"
            }
            DataBaseUtils.user = user
            navigator?.openHomeScreen()

        } catch (ex: Exception) {
            loading.value = false
            messageLiveData.value = ex.localizedMessage

        }
    }


    fun validate(): Boolean {
        var valid = true
        if (email.get().isNullOrBlank()) {
            emailError.set("Please enter Your email")
            valid = false

        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            emailError.set("Please enter Your Password")
            valid = false

        } else {
            emailError.set(null)
        }

        return valid
    }
}