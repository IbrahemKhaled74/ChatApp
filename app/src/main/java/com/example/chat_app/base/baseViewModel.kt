package com.example.chat_app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class baseViewModel<N>:ViewModel() {
    var messageLiveData= MutableLiveData<String>()
    var loading= MutableLiveData<Boolean>()
    var toastMessage=MutableLiveData<String>()
    var navigator:N?=null

}