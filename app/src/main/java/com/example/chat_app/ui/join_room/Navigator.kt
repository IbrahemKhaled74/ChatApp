package com.example.chat_app.ui.join_room

import com.example.domain.models.Room

interface Navigator {
    fun goToChat(room: Room)
    fun gotoHome()
}