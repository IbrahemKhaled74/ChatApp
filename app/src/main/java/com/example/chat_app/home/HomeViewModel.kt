package com.example.chat_app.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.base.baseViewModel
import com.example.chat_app.dataBase.DataBaseUtils
import com.example.chat_app.dataBase.getRoomFromFireStore
import com.example.chat_app.dataBase.getUserRoomRef
import com.example.chat_app.join_room.JoinRoom

class HomeViewModel : baseViewModel<Navigator>() {

    val user = DataBaseUtils.user
    var rooms = MutableLiveData<List<Room>>()

    fun goToCreateRoom() {
        navigator?.openCreateNewRoom()
    }

    fun openDrawer() {
        navigator?.openDrawer()
    }

    fun getRoom() {
        getRoomFromFireStore(onSuccessListener = {
            val room = it.toObjects(Room::class.java)
            Log.e("TAG", "onStart: ${rooms} ")
            rooms.value = room
        }, onFailureListener = {
            messageLiveData.value = it.localizedMessage
        })

    }

    fun startChat(room: Room) {
        getUserRoomRef(roomId = room.id, DataBaseUtils.user?.id, onSuccessListener = {
            val user = it.toObject(JoinRoom::class.java)
            Log.e("TAG", "${user?.userId}: ")

            if (user != null) {
                Log.e("TAG", "${user.userId}: ")
                navigator?.goToChat(room)

            } else {
                navigator?.goToJoin(room)

            }
        }, onFailureListener = {
            messageLiveData.value = it.localizedMessage
        })


    }

    fun logOut() {
        navigator?.goToLogin()
        DataBaseUtils.user = null

    }

}