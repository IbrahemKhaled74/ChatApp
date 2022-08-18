package com.example.chat_app.createRoom

import com.example.chat_app.R

data class Category(
    var id:String?=null,
    var roomName:String?=null,
    var ImageID:Int?=null,
){
    companion object{
        const val music="Music"
        const val sport="Sports"
        const val programming="Programming"

        fun getCategory(categoryID: String):Category{
            return when(categoryID){
                music-> {
                    Category(music,"Music", R.drawable.music )
                }
                sport-> {
                    Category(sport,"Sports",R.drawable.sports)
                }
                else-> Category(programming,"Programming",R.drawable.coding)

            }
        }
        fun getCategoryList():List<Category>{
            return listOf(
                getCategory(music),
                getCategory(sport),
                getCategory(programming),


                )
        }

    }
}
