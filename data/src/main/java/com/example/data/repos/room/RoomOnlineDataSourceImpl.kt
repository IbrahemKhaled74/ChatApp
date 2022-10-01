package com.example.data.repos.room

import com.example.domain.models.JoinRoom
import com.example.domain.models.Room
import com.example.domain.repos.RoomOnlineDataSource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RoomOnlineDataSourceImpl
    : RoomOnlineDataSource {
    private val db = Firebase.firestore

    override suspend fun createRoomInFirebase(room: Room) {

        try {
            val coll = db.collection(Room.COLLECTION_NAME)

            val doc = coll.document()
            room.id = doc.id
            doc.set(room)
                .await()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun addUserToRoom(room: Room, joinRoom: JoinRoom) {

        try {
            val joinRoomRef = db.collection(Room.COLLECTION_NAME)
                .document(room.id!!)

            val joinRoomDoc = joinRoomRef.collection(JoinRoom.COLLECTION_NAME)
                .document(joinRoom.userId!!)

            joinRoomDoc.set(joinRoom)
                .await()


        } catch (ex: Exception) {
            throw ex
        }


    }

    override suspend fun knowNumberJoined(roomId: String): QuerySnapshot {
        try {
            return db.collection(Room.COLLECTION_NAME).document(roomId)
                .collection(JoinRoom.COLLECTION_NAME)
                .get()
                .await()
        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun updateNumber(roomId: String, size: Int) {
        try {
            db.collection(Room.COLLECTION_NAME).document(roomId)
                .update(JoinRoom.MEMBER_NUMBER, size)
                .await()

        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun getRoomFromFireBase(): QuerySnapshot {

        try {
            return db.collection(Room.COLLECTION_NAME)
                .get()
                .await()

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun openChat(roomId: String?, userid: String?): DocumentSnapshot {
        try {
            return db.collection(Room.COLLECTION_NAME)
                .document(roomId!!)
                .collection(JoinRoom.COLLECTION_NAME)
                .document(userid!!)
                .get()
                .await()

        } catch (ex: Exception) {
            throw ex
        }
    }

}