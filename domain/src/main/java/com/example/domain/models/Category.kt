package com.example.domain.models

import com.example.domain.R


data class Category(
    var id: String? = null,
    var roomName: String? = null,
    var ImageID: Int? = null,
) {
    private val music = "Music"
    private val sport = "Sports"
    private val programming = "Programming"

    fun getCategory(categoryID: String): Category {
        return when (categoryID) {
            music -> {
                Category(music, "Music", R.drawable.music)
            }
            sport -> {
                Category(sport, "Sports", R.drawable.sports)
            }
            else -> Category(programming, "Programming", R.drawable.coding)

        }
    }

    fun getCategoryList(): List<Category> {
        return listOf(
            getCategory(music),
            getCategory(sport),
            getCategory(programming),


            )
    }


}
