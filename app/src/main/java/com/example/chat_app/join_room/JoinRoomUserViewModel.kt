package com.example.chat_app.join_room

import android.util.Log
import com.example.chat_app.base.baseViewModel
import com.example.chat_app.dataBase.DataBaseUtils
import com.example.chat_app.dataBase.JoinRooms
import com.example.chat_app.dataBase.knowNumber
import com.example.chat_app.dataBase.updateNumber
import com.example.chat_app.home.Room
import com.google.android.gms.tasks.OnFailureListener

class JoinRoomUserViewModel : baseViewModel<Navigator>() {
    var room: Room? = null
    var joinRoom = JoinRoom()

    fun GoToChatRoom() {
        addUserJoin()
    }

    fun goToHome() {
        navigator?.gotoHome()
    }

    fun addUserJoin() {

        joinRoom =
            JoinRoom(userName = DataBaseUtils.user?.userName, userId = DataBaseUtils.user?.id)
        JoinRooms(room = room!!, joinRoom, onSuccessListener = {
            knowNumber(room!!.id, onSuccessListener = {
                val size = it.size()
                Log.e("Size ", "addUserJoin: $size")
                updateNumber(roomId = room?.id, member = size, onSuccessListener = {

                }, onFailureListener = {

                })


            }, onFailureListener = {})

            navigator?.goToChat(room!!)
        }, OnFailureListener {
            messageLiveData.value = it.localizedMessage

        })


    }


}