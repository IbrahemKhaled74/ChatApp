package com.example.data.repos.room

import com.example.domain.models.JoinRoom
import com.example.domain.models.Room
import com.example.domain.repos.RoomOnlineDataSource
import com.example.domain.repos.RoomRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class RoomRepositoryImpl(val roomOnlineDataSource: RoomOnlineDataSource) : RoomRepository {
    override suspend fun createRoomInFirebase(room: Room) {
        try {
            roomOnlineDataSource.createRoomInFirebase(room)
        } catch (ex: Exception) {
            throw ex

        }
    }

    override suspend fun addUserToRoom(room: Room, joinRoom: JoinRoom) {
        try {
            roomOnlineDataSource.addUserToRoom(room, joinRoom)
        } catch (ex: Exception) {
            throw ex

        }
    }

    override suspend fun knowNumberJoined(roomId: String): QuerySnapshot {
        try {
            return roomOnlineDataSource.knowNumberJoined(roomId)
        } catch (ex: Exception) {
            throw ex

        }
    }

    override suspend fun updateNumber(roomId: String, size: Int) {
        try {
            roomOnlineDataSource.updateNumber(roomId, size)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getRoomFromFireBase(): QuerySnapshot {
        try {
            return roomOnlineDataSource.getRoomFromFireBase()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun openChat(roomId: String?, userid: String?): DocumentSnapshot {
        try {
            return roomOnlineDataSource.openChat(roomId, userid)

        } catch (ex: Exception) {
            throw ex

        }
    }
}