package com.example.chat_app.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.chat_app.base.baseViewModel
import com.example.domain.models.AppUser
import com.example.domain.repos.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject constructor(val userRepository: UserRepository) :
    baseViewModel<navigator>() {
    var userName = ObservableField<String>()
    var userNameError = ObservableField<String>()
    var firstName = ObservableField<String>()
    var firstNameError = ObservableField<String>()
    var lastName = ObservableField<String>()
    var lastNameError = ObservableField<String>()
    var email = ObservableField<String>()
    var emailError = ObservableField<String>()
    var password = ObservableField<String>()
    var passwordError = ObservableField<String>()

    fun createAccount() {

        if (validated()) {
            viewModelScope.launch {
                createAccountInFireBase()

            }


        }
    }

    private suspend fun createAccountInFireBase() {
        loading.value = true
        try {
            val task = userRepository.createAccountInFireBase(email.get()!!, password.get()!!)
            loading.value = false
            Log.e("Done", "creatAccount:sucess ")
            messageLiveData.value = "Created Success"
            logIntoFireStore(task.user?.uid)

        } catch (ex: Exception) {
            loading.value = false
            Log.e("Not Done", ex.localizedMessage)
            messageLiveData.value = ex.localizedMessage

        }
    }


    private suspend fun logIntoFireStore(uid: String?) {
        loading.value = true
        val newUser = AppUser(
            id = uid,
            userName = userName.get(),
            firstName = firstName.get(),
            lastName = lastName.get(),
            email = email.get(),
        )
        try {
            userRepository.logInFireBase(newUser)
            loading.value = false
            navigator?.openLoginScreen()

        } catch (ex: Exception) {
            loading.value = false
            toastMessage.value = ex.localizedMessage

        }
    }


    private fun validated(): Boolean {
        var valid = true
        if (userName.get().isNullOrBlank()) {
            userNameError.set("Please enter User Name")
            valid = false
        } else {
            userNameError.set(null)
        }
        if (lastName.get().isNullOrBlank()) {
            lastNameError.set("Please enter Last Name")
            valid = false
        } else {
            lastNameError.set(null)
        }
        if (firstName.get().isNullOrBlank()) {
            firstNameError.set("Please enter First Name")
            valid = false
        } else {
            firstNameError.set(null)
        }
        if (email.get().isNullOrBlank()) {
            emailError.set("Please enter Your email")
            valid = false
        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            passwordError.set("Please enter Your Password")
            valid = false
        } else {
            passwordError.set(null)
        }
        return valid
    }

}