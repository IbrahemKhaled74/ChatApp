package com.example.chat_app.home

interface Navigator {
    fun openCreateNewRoom()
    fun openDrawer()
    fun goToChat(room: Room)
    fun goToJoin(room: Room)
    fun goToLogin()
}