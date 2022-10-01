package com.example.chat_app.ui.chat

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.constant.Constants
import com.example.chat_app.databinding.ActivityChatScreenBinding
import com.example.chat_app.ui.home.HomeScreen
import com.example.domain.models.Room
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatScreen : baseActivity<ActivityChatScreenBinding, ChatViewModel>(), Navigator {
    @Inject
    lateinit var chatAdaptor: ChatAdaptor
    lateinit var chatLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.item = vm
        vm.navigator = this
        val room: Room? = intent.getParcelableExtra(Constants.room)
        vm.room = room

        chatLayoutManager = LinearLayoutManager(this)
        dataBinding.recycleView.layoutManager = chatLayoutManager
        chatLayoutManager.stackFromEnd = true
        dataBinding.recycleView.adapter = chatAdaptor
        lifecycleScope.launch {
            getChatMessage()

        }
        leaveRoom(room!!)


    }

    private suspend fun getChatMessage() {
        vm.liveChat()
        vm.messageLists.observe(this, { messageList ->
            chatAdaptor.setData(messageList)
            dataBinding.recycleView.smoothScrollToPosition(chatAdaptor.itemCount)

        })

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_chat_screen
    }

    override fun getVMClass(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun goToRoom() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun leaveRoom(room: Room) {
        dataBinding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.leave) {
                lifecycleScope.launch {
                    vm.leaveRooms(room)

                }
            }
            return@setOnMenuItemClickListener true
        }

    }

    override fun goToHome() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)

    }

}