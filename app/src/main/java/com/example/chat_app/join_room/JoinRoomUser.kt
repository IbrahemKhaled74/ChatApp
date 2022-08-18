package com.example.chat_app.join_room

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.Constants
import com.example.chat_app.Constants.room
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.chat.ChatScreen
import com.example.chat_app.databinding.ActivityJoinRoomUserBinding
import com.example.chat_app.home.HomeScreen
import com.example.chat_app.home.Room

class JoinRoomUser : baseActivity<ActivityJoinRoomUserBinding,JoinRoomUserViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm=vm
        vm.navigator=this
        val room: Room? =intent.getParcelableExtra(Constants.room)
        vm.room=room

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_join_room_user
    }

    override fun getVMClass(): JoinRoomUserViewModel {
        return ViewModelProvider(this)[JoinRoomUserViewModel::class.java]
    }

    override fun goToChat(room: Room) {
        val intent= Intent(this, ChatScreen::class.java)
            .putExtra(Constants.room,room )
        startActivity(intent)

    }

    override fun gotoHome() {
        val intent= Intent(this, HomeScreen::class.java)
        startActivity(intent)
    }

}