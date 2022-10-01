package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var id: String? = null,
    val name: String? = null,
    val desc: String? = null,
    val categoryID: String? = null,
    var userID: String? = null,
    var memberNumber: Int? = 0
) : Parcelable {
    companion object {
        const val COLLECTION_NAME = "Room"

    }

    private val music = "Music"
    fun getImage(): Int? {
        return Category().getCategory(
            categoryID ?: music
        ).ImageID
    }

    fun getNumber() = "$memberNumber"


}
