package com.example.chat_app.join_room

import com.example.chat_app.home.Room

interface Navigator {
    fun goToChat(room: Room)
    fun gotoHome()
}