package com.example.chat_app.chat

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.base.baseViewModel
import com.example.chat_app.dataBase.*
import com.example.chat_app.home.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import java.util.*

class ChatViewModel : baseViewModel<Navigator>() {
    var message = ObservableField<String>()
    var room: Room? = null
    val messageLists = MutableLiveData<MutableList<Message>>()


    fun sendMessage() {
        addMessage()
        message.set("")
    }

    fun endActivity() {
        navigator?.goToRoom()
    }

    fun addMessage() {
        val message = Message(
            message = message.get(),
            senderName = DataBaseUtils.user?.userName,
            senderId = DataBaseUtils.user?.id,
            roomId = room?.id!!,
            time = Date().time,
        )
        addMessageToFireStore(message, onSuccessListener = {
            Log.e("TAG", "addMessage: ")
        }, onFailureListener = {
            Log.e("TAG", "addMessage: ")
        })
    }

    fun leaveRooms(room: Room?) {
        removeUserFromFireStore(room?.id, DataBaseUtils.user?.id, onSuccessListener = {
            knowNumber(room!!.id, onSuccessListener = {
                val size = it.size()
                Log.e("Size ", "addUserJoin: $size")
                updateNumber(roomId = room.id, member = size, onSuccessListener = {
                    navigator?.goToHome()
                }, onFailureListener = {
                    toastMessage.value = it.localizedMessage

                })


            }, onFailureListener = {
                toastMessage.value = it.localizedMessage

            })

        }, onFailureListener = {
            toastMessage.value = it.localizedMessage

        })


    }

    fun liveChat() {
        roomRef(room?.id!!)
            .orderBy("time", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    toastMessage.value = e.localizedMessage
                }
                for (dc in snapshots!!.documentChanges) {
                    val messageList = mutableListOf<Message>()
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val message = dc.document.toObject(Message::class.java)
                            messageList.add(message)
                            messageLists.value = messageList
                        }
                    }
                }
            }

    }
}