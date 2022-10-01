package com.example.data.repos.chat

import androidx.lifecycle.MutableLiveData
import com.example.domain.models.Message
import com.example.domain.repos.ChatOnlineDataSource
import com.example.domain.repos.ChatRepository
import com.google.firebase.firestore.CollectionReference

class ChatRepositoryImpl(val chatOnlineDataSource: ChatOnlineDataSource) : ChatRepository {
    override suspend fun addMessageToFireBase(message: Message) {

        try {
            chatOnlineDataSource.addMessageToFireBase(message)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun removeUserFromFireBase(roomId: String?, userid: String?) {
        try {
            chatOnlineDataSource.removeUserFromFireBase(roomId, userid)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun liveChat(
        roomId: String,
        message: MutableLiveData<String>,
        messageLists: MutableLiveData<MutableList<Message>>
    ) {
        try {
            chatOnlineDataSource.liveChat(
                roomId, message,
                messageLists
            )

        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun roomRef(roomId: String): CollectionReference {
        try {
            return chatOnlineDataSource.roomRef(roomId)

        } catch (ex: Exception) {
            throw ex
        }
    }


}