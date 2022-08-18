package com.example.chat_app.dataBase

data class AppUser(
   val id:String?=null,
   val userName:String?=null,
   val firstName:String?=null,
   val lastName:String?=null,
   val email:String?=null
){
    companion object{
        const val COLLECTION_NAME="Users"
    }
}
