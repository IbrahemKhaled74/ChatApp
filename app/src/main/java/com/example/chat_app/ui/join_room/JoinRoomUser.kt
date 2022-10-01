package com.example.chat_app.ui.join_room

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.constant.Constants
import com.example.chat_app.databinding.ActivityJoinRoomUserBinding
import com.example.chat_app.ui.chat.ChatScreen
import com.example.chat_app.ui.home.HomeScreen
import com.example.domain.models.Room
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinRoomUser : baseActivity<ActivityJoinRoomUserBinding, JoinRoomUserViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = vm
        vm.navigator = this
        val room: Room? = intent.getParcelableExtra(Constants.room)
        vm.room = room

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_join_room_user
    }

    override fun getVMClass(): JoinRoomUserViewModel {
        return ViewModelProvider(this)[JoinRoomUserViewModel::class.java]
    }

    override fun goToChat(room: com.example.domain.models.Room) {
        val intent = Intent(this, ChatScreen::class.java)
            .putExtra(Constants.room, room)
        startActivity(intent)

    }

    override fun gotoHome() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
    }

}