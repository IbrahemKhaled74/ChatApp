package com.example.chat_app.ui.home

import com.example.domain.models.Room


interface Navigator {
    fun openCreateNewRoom()
    fun openDrawer()
    fun goToChat(room: Room)
    fun goToJoin(room: Room)
    fun goToLogin()
}