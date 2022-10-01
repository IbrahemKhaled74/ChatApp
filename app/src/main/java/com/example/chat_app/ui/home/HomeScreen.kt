package com.example.chat_app.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.constant.Constants
import com.example.chat_app.databinding.ActivityHomeScreenBinding
import com.example.chat_app.ui.chat.ChatScreen
import com.example.chat_app.ui.createRoom.CreateRoom
import com.example.chat_app.ui.join_room.JoinRoomUser
import com.example.chat_app.ui.login.Login
import com.example.domain.models.Room
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : baseActivity<ActivityHomeScreenBinding, HomeViewModel>(), Navigator {
    @Inject
    lateinit var roomAdaptor: RoomAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vmm = vm
        vm.navigator = this
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_home_screen
    }

    override fun getVMClass(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    fun subscribe() {
        vm.rooms.observe(this, {
            roomAdaptor.changeData(it)

        })
    }

    override fun onStart() {
        super.onStart()
        dataBinding.RV.adapter = roomAdaptor
        subscribe()
        lifecycleScope.launch {
            vm.getRoom()

        }
        roomAdaptor.onRoomClick = object : RoomAdaptor.onRoomClickListener {
            override fun onItemClick(position: Int, room: Room) {
                lifecycleScope.launch {
                    vm.startChat(room)

                }
            }
        }


    }


    override fun openCreateNewRoom() {
        val intent = Intent(this, CreateRoom::class.java)
        startActivity(intent)
    }

    @SuppressLint("WrongConstant")
    override fun openDrawer() {
        dataBinding.drawerLayout.openDrawer(Gravity.START)
    }


    override fun goToChat(room: Room) {
        val intent = Intent(this, ChatScreen::class.java)
            .putExtra(Constants.room, room)
        startActivity(intent)

    }

    override fun goToJoin(room: Room) {
        val intent = Intent(this, JoinRoomUser::class.java)
            .putExtra(Constants.room, room)
        startActivity(intent)

    }

    override fun goToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()

    }

}