package com.example.chat_app.home

import android.os.Parcelable
import com.example.chat_app.createRoom.Category
import kotlinx.parcelize.Parcelize
@Parcelize
data class Room(
    var id:String?=null,
    val name:String?=null,
    val desc:String?=null,
    val categoryID:String?=null,
    var userID:String?=null,
    var memberNumber: Int ?=0
):Parcelable{
    companion object{
       const val COLLECTION_NAME="Room"

    }
    fun getImage(): Int? {
       return Category.getCategory(categoryID?:Category.music).ImageID
    }
    fun getNumber()= "$memberNumber"
}
