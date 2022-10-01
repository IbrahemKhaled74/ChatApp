package com.example.chat_app.ui.join_room

import androidx.lifecycle.viewModelScope
import com.example.chat_app.base.baseViewModel
import com.example.domain.models.DataBaseUtils
import com.example.domain.models.JoinRoom
import com.example.domain.models.Room
import com.example.domain.repos.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinRoomUserViewModel
@Inject constructor(val roomRepository: RoomRepository) :
    baseViewModel<Navigator>() {
    var room: Room? = null
    var joinRoom = JoinRoom()

    fun goToChatRoom() {
        viewModelScope.launch {
            addUserJoin()
        }
    }

    fun goToHome() {
        navigator?.gotoHome()
    }

    suspend fun addUserJoin() {

        joinRoom =
            JoinRoom(
                userName = DataBaseUtils.user?.userName,
                userId = DataBaseUtils.user?.id
            )
        loading.value = true
        try {
            roomRepository.addUserToRoom(room!!, joinRoom)
            loading.value = false
            try {
                val size = roomRepository.knowNumberJoined(room?.id!!).size()

                try {
                    roomRepository.updateNumber(room?.id!!, size)

                } catch (ex: Exception) {

                    toastMessage.value = ex.localizedMessage
                }

            } catch (ex: Exception) {
                toastMessage.value = ex.localizedMessage
            }

            navigator?.goToChat(room!!)

        } catch (ex: Exception) {
            loading.value = false

            messageLiveData.value = ex.localizedMessage

        }

    }


}