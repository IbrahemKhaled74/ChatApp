package com.example.chat_app.ui.chat

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chat_app.base.baseViewModel
import com.example.domain.models.DataBaseUtils
import com.example.domain.models.Message
import com.example.domain.models.Room
import com.example.domain.repos.ChatRepository
import com.example.domain.repos.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val chatRepository: ChatRepository,
    val roomRepository: RoomRepository,

    ) : baseViewModel<Navigator>() {
    var message = ObservableField<String>()
    var room: Room? = null
    val messageLists = MutableLiveData<MutableList<Message>>()


    fun sendMessage() {
        viewModelScope.launch {
            addMessage()
            message.set("")
        }
    }

    fun endActivity() {
        navigator?.goToRoom()
    }

    suspend fun addMessage() {
        val message = Message(
            message = message.get(),
            senderName = DataBaseUtils.user?.userName,
            senderId = DataBaseUtils.user?.id,
            roomId = room?.id!!,
            time = Date().time,
        )
        try {
            chatRepository.addMessageToFireBase(message)

        } catch (ex: Exception) {
            toastMessage.value = ex.localizedMessage

        }
    }

    suspend fun leaveRooms(room: Room?) {
        try {
            chatRepository.removeUserFromFireBase(room?.id, DataBaseUtils.user?.id)
            try {
                val known = roomRepository.knowNumberJoined(room?.id!!)
                val size = known.size()
                Log.e("Size ", "addUserJoin: $size")

                try {
                    roomRepository.updateNumber(room.id!!, size)
                    navigator?.goToHome()

                } catch (ex: Exception) {
                    toastMessage.value = ex.localizedMessage

                }


            } catch (ex: Exception) {
                toastMessage.value = ex.localizedMessage

            }


        } catch (ex: Exception) {
            toastMessage.value = ex.localizedMessage

        }


    }

    suspend fun liveChat() {
        chatRepository.liveChat(room?.id!!, toastMessage, messageLists)

//         roomRef(room?.id!!)
//             .orderBy("time", Query.Direction.ASCENDING)
//             .addSnapshotListener { snapshots, e ->
//                 if (e != null) {
//                     toastMessage.value = e.localizedMessage
//                 }
//                 for (dc in snapshots!!.documentChanges) {
//                     val messageList = mutableListOf<Message>()
//                     when (dc.type) {
//                         DocumentChange.Type.ADDED -> {
//                             val message = dc.document.toObject(Message::class.java)
//                             messageList.add(message)
//                             messageLists.value = messageList
//                         }
//                     }
//                 }
//             }

    }
}