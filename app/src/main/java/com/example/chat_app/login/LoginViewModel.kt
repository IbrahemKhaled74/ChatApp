package com.example.chat_app.login
import android.util.Log
import androidx.databinding.ObservableField
import com.example.chat_app.base.baseViewModel
import com.example.chat_app.dataBase.AppUser
import com.example.chat_app.dataBase.DataBaseUtils
import com.example.chat_app.dataBase.signInFireStore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: baseViewModel<Navigator>() {
    var email= ObservableField<String>()
    var emailError= ObservableField<String>()
    var password= ObservableField<String>()
    var passwordError= ObservableField<String>()


    fun logIn(){
        if (validted()){
            getAccountFromFireBase()
        }
    }
    fun createNewAccount(){
        navigator?.openRegesterationScreen()

    }
    private fun getAccountFromFireBase() {
        val auth= Firebase.auth
        loading.value=true
        auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    loading.value=false

                    Log.e("Done", "creatAccount:sucess ",)
                   getUserFromFireStore(task.result.user?.uid)

                } else {
                    loading.value=false

                    Log.e("Not Done", task.exception?.message.toString(),)
                    messageLiveData.value = task.exception?.message


                }
            }

    }

    private fun getUserFromFireStore(uid: String?) {
        loading.value=true
        signInFireStore(uid, onSuccessListener = {
         loading.value=false
         val user:AppUser?=it.toObject(AppUser::class.java)
            DataBaseUtils.user=user
            if (user==null){
                messageLiveData.value="Invalid Email Or Password"
                return@signInFireStore
            }
            navigator?.openHomeScreen()
        }, onFailureListener = {
           loading.value=false
           messageLiveData.value=it.localizedMessage
        })

    }


    fun validted():Boolean{
        var valid=true
        if(email.get().isNullOrBlank()){
            emailError.set("Please enter Your email")
            valid=false

        }else{
            emailError.set(null)
        }
        if(password.get().isNullOrBlank()){
            emailError.set("Please enter Your Password")
            valid=false

        }else{
            emailError.set(null)
        }

        return valid
    }
}