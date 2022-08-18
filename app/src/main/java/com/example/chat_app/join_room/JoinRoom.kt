package com.example.chat_app.join_room

data class JoinRoom (
    val userId:String?=null,
    val userName:String?=null
) {
    companion object{
        const val COLLECTION_NAME="User_Room"
        const val MEMBER_NUMBER="memberNumber"
    }
}
