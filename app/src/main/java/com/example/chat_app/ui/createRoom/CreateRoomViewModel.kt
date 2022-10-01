package com.example.chat_app.ui.createRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.chat_app.base.baseViewModel
import com.example.domain.models.Category
import com.example.domain.models.DataBaseUtils
import com.example.domain.models.Room
import com.example.domain.repos.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRoomViewModel
@Inject constructor(
    val roomRepository: RoomRepository, category: Category
) :
    baseViewModel<Navigator>() {
    var roomName = ObservableField<String>()
    var roomNameError = ObservableField<String>()
    var roomDesc = ObservableField<String>()
    var roomDescError = ObservableField<String>()
    val categoryList = category.getCategoryList()
    var selectedCategory = categoryList[0]

    fun createRoom() {
        if (validted()) {
            viewModelScope.launch {
                createRoomInFS()
            }
        }
    }

    private suspend fun createRoomInFS() {
        loading.value = true
        val room = Room(
            name = roomName.get(),
            desc = roomDesc.get(),
            categoryID = selectedCategory.id,
            userID = DataBaseUtils.user?.id,

            )
        try {
            roomRepository.createRoomInFirebase(room)
            loading.value = false
            navigator?.goToHomeScreen()
            toastMessage.value = "Room Added"

        } catch (ex: Exception) {
            loading.value = false
            messageLiveData.value = ex.localizedMessage

        }
    }


    private fun validted(): Boolean {
        var valid = true
        if (roomName.get().isNullOrBlank()) {
            roomNameError.set("Please enter Your email")
            valid = false

        } else {
            roomNameError.set(null)
        }
        if (roomDesc.get().isNullOrBlank()) {
            roomDescError.set("Please enter Your Password")
            valid = false

        } else {
            roomDescError.set(null)
        }

        return valid
    }


}