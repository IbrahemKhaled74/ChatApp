package com.example.domain.repos

import androidx.lifecycle.MutableLiveData
import com.example.domain.models.Message
import com.google.firebase.firestore.CollectionReference


interface ChatRepository {
    suspend fun addMessageToFireBase(message: Message)
    suspend fun removeUserFromFireBase(roomId: String?, userid: String?)
    suspend fun liveChat(
        roomId: String, message: MutableLiveData<String>,
        messageLists: MutableLiveData<MutableList<Message>>
    )

    fun roomRef(roomId: String): CollectionReference


}

interface ChatOnlineDataSource {
    suspend fun addMessageToFireBase(message: Message)
    suspend fun removeUserFromFireBase(roomId: String?, userid: String?)
    suspend fun liveChat(
        roomId: String, message: MutableLiveData<String>,
        messageLists: MutableLiveData<MutableList<Message>>
    )

    fun roomRef(roomId: String): CollectionReference


}