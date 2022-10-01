package com.example.chat_app.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.base.baseViewModel
import com.example.domain.models.DataBaseUtils
import com.example.domain.models.JoinRoom
import com.example.domain.models.Room
import com.example.domain.repos.RoomRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(val roomRepository: RoomRepository) :
    baseViewModel<Navigator>() {

    val user = DataBaseUtils.user
    var rooms = MutableLiveData<List<Room>>()

    fun goToCreateRoom() {
        navigator?.openCreateNewRoom()
    }

    fun openDrawer() {
        navigator?.openDrawer()
    }

    suspend fun getRoom() {
        loading.value = true
        try {
            val room = roomRepository.getRoomFromFireBase().toObjects(Room::class.java)
            loading.value = false
            rooms.value = room

        } catch (ex: Exception) {
            loading.value = false
            messageLiveData.value = ex.localizedMessage

        }

    }

    suspend fun startChat(room: Room) {
        loading.value = true
        try {
            val user = roomRepository.openChat(room.id, DataBaseUtils.user?.id)
                .toObject(JoinRoom::class.java)
            if (user != null) {
                Log.e("TAG", "${user.userId}: ")
                loading.value = false
                navigator?.goToChat(room)

            } else {
                loading.value = false

                navigator?.goToJoin(room)

            }
        } catch (ex: Exception) {
            loading.value = false

            messageLiveData.value = ex.localizedMessage

        }


    }

    fun logOut() {
        Firebase.auth.signOut()
        navigator?.goToLogin()
        DataBaseUtils.user = null
    }

}