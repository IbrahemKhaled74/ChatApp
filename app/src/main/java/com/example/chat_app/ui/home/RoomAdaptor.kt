package com.example.chat_app.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.databinding.RoomItemBinding
import com.example.domain.models.Room

class RoomAdaptor(var room: List<Room>?) : RecyclerView.Adapter<RoomAdaptor.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: RoomItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.room_item, parent, false
        )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(room = room?.get(position)!!)
        holder.itemView.setOnClickListener {
            onRoomClick?.onItemClick(position, room!![position])
        }
    }

    override fun getItemCount(): Int = room?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(room: List<Room>?) {
        this.room = room
        notifyDataSetChanged()
    }

    var onRoomClick: onRoomClickListener? = null

    interface onRoomClickListener {
        fun onItemClick(position: Int, room: Room)
    }

    class viewHolder(val roomItemBinding: RoomItemBinding) :
        RecyclerView.ViewHolder(roomItemBinding.root) {
        fun bind(room: Room) {
            roomItemBinding.item = room
            roomItemBinding.invalidateAll()

        }

    }

}