package com.example.domain.repos

import com.example.domain.models.JoinRoom
import com.example.domain.models.Room
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface RoomRepository {
    suspend fun createRoomInFirebase(room: Room)
    suspend fun addUserToRoom(room: Room, joinRoom: JoinRoom)
    suspend fun knowNumberJoined(roomId: String): QuerySnapshot
    suspend fun updateNumber(roomId: String, size: Int)
    suspend fun getRoomFromFireBase(): QuerySnapshot
    suspend fun openChat(roomId: String?, userid: String?): DocumentSnapshot

}

interface RoomOnlineDataSource {
    suspend fun createRoomInFirebase(room: Room)
    suspend fun addUserToRoom(room: Room, joinRoom: JoinRoom)
    suspend fun knowNumberJoined(roomId: String): QuerySnapshot
    suspend fun updateNumber(roomId: String, size: Int)
    suspend fun getRoomFromFireBase(): QuerySnapshot
    suspend fun openChat(roomId: String?, userid: String?): DocumentSnapshot


}