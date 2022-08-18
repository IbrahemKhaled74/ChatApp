package com.example.chat_app.createRoom

import androidx.databinding.ObservableField
import com.example.chat_app.base.baseViewModel
import com.example.chat_app.dataBase.DataBaseUtils
import com.example.chat_app.dataBase.addRoomToFireStore
import com.example.chat_app.home.Room

class CreatRoomViewModel : baseViewModel<Navigator>() {
    var roomName = ObservableField<String>()
    var roomNameError = ObservableField<String>()
    var roomDesc = ObservableField<String>()
    var roomDescError = ObservableField<String>()
    val categoryList = Category.getCategoryList()
    var selectedCategory = categoryList[0]

    fun createRoom() {
        if (validted()) {
            createRoomInFS()
        }
    }

    fun createRoomInFS() {
        loading.value = true
        val room = Room(
            name = roomName.get(),
            desc = roomDesc.get(),
            categoryID = selectedCategory.id,
            userID = DataBaseUtils.user?.id,

            )
        addRoomToFireStore(room,
            onSuccessListener = {
                loading.value = false
                navigator?.goToHomeScreen()
                toastMessage.value = "Room Added"
            },

            onFailureListener = {
                loading.value = false
                messageLiveData.value = it.localizedMessage
            }
        )
    }


    fun validted(): Boolean {
        var valid = true
        if (roomName.get().isNullOrBlank()) {
            roomNameError.set("Please enter Your email")
            valid = false

        } else {
            roomNameError.set(null)
        }
        if (roomDesc.get().isNullOrBlank()) {
            roomDescError.set("Please enter Your Password")
            valid = false

        } else {
            roomDescError.set(null)
        }

        return valid
    }


}