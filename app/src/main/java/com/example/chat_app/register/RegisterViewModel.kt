package com.example.chat_app.register

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chat_app.base.baseViewModel
import com.example.chat_app.dataBase.AppUser
import com.example.chat_app.dataBase.addAccountToFireStore
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel: baseViewModel<navigator>() {
    var userName=ObservableField<String>()
    var userNameError=ObservableField<String>()
    var firstName=ObservableField<String>()
    var firstNameError=ObservableField<String>()
    var lastName=ObservableField<String>()
    var lastNameError=ObservableField<String>()
    var email=ObservableField<String>()
    var emailError=ObservableField<String>()
    var password=ObservableField<String>()
    var passwordError=ObservableField<String>()

    fun creatAccount(){

        if (isValided()){
            createAccountInFireBase()


        }
    }

    private fun createAccountInFireBase() {
        val auth=Firebase.auth
        loading.value=true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loading.value=false

//                        Log.e("Done", "creatAccount:sucess ",)
                       // messageLiveData.value="Created Success"
                        logIntoFireStore(task.result.user?.uid)


                    } else {
                        loading.value=false
                        Log.e("Not Done", task.exception?.message.toString(),)
                        messageLiveData.value = task.exception?.message


                    }
                }

    }
    private fun logIntoFireStore(uid:String?) {
        loading.value=true
        val newUser= AppUser(
            id = uid,
            userName = userName.get(),
            firstName = firstName.get(),
            lastName = lastName.get(),
            email = email.get(),
        )
        addAccountToFireStore(newUser, onSuccessListener = OnSuccessListener{
        loading.value=false
            navigator?.openLoginScreen()
        }, onFailureListener = {
            loading.value=false
            messageLiveData.value=it.localizedMessage
        })
    }


    private fun isValided(): Boolean {
        var valid=true
        if (userName.get().isNullOrBlank()){
            userNameError.set("Please enter User Name")
            valid=false
        }else{
            userNameError.set(null)
        }
        if (lastName.get().isNullOrBlank()){
            lastNameError.set("Please enter Last Name")
            valid=false
        }else{
            lastNameError.set(null)
        }
        if (firstName.get().isNullOrBlank()){
            firstNameError.set("Please enter First Name")
            valid=false
        }else{
            firstNameError.set(null)
        }
        if (email.get().isNullOrBlank()){
            emailError.set("Please enter Your email")
            valid=false
        }else{
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()){
            passwordError.set("Please enter Your Password")
            valid=false
        }else{
            passwordError.set(null)
        }
return valid
    }

}