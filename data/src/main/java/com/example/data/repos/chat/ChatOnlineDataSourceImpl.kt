package com.example.data.repos.chat

import androidx.lifecycle.MutableLiveData
import com.example.domain.models.JoinRoom
import com.example.domain.models.Message
import com.example.domain.models.Room
import com.example.domain.repos.ChatOnlineDataSource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ChatOnlineDataSourceImpl
    : ChatOnlineDataSource {
    private val fs = Firebase.firestore

    override fun roomRef(roomId: String): CollectionReference {
        return fs.collection(Room.COLLECTION_NAME)
            .document(roomId)
            .collection(Message.COLLECTION_NAME)


    }

    override suspend fun addMessageToFireBase(message: Message) {
        try {
            val roomRef = roomRef(message.roomId!!)

            val messageDoc = roomRef.document()
            message.id = messageDoc.id
            messageDoc.set(message)
                .await()


        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun removeUserFromFireBase(
        roomId: String?, userid: String?
    ) {
        try {
            fs.collection(Room.COLLECTION_NAME)
                .document(roomId!!)
                .collection(JoinRoom.COLLECTION_NAME)
                .document(userid!!)
                .delete()
                .await()

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
            roomRef(roomId)
                .orderBy("time", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshots, e ->
                    if (e != null) {
                        message.value = e.localizedMessage
                    } else {
                        for (dc in snapshots!!.documentChanges) {
                            val messageList = mutableListOf<Message>()
                            when (dc.type) {
                                DocumentChange.Type.ADDED -> {
                                    val message = dc.document.toObject(Message::class.java)
                                    messageList.add(message)
                                    messageLists.value = messageList


                                }
                                else -> {}
                            }
                        }
                    }

                }
        } catch (ex: Exception) {
            throw ex
        }


    }
}