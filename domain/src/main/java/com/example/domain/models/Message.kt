package com.example.domain.models

import java.text.SimpleDateFormat
import java.util.*

data class Message(
    var id: String? = null,
    var message: String? = null,
    var senderName: String? = null,
    var senderId: String? = null,
    var time: Long? = null,
    var roomId: String? = null

) {
    companion object {
        const val COLLECTION_NAME = "Messages"
    }


    fun formatTime(): String {
        val date = Date(time!!)
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(date)
    }
}