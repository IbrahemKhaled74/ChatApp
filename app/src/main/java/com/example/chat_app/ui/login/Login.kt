package com.example.chat_app.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.databinding.ActivityLoginBinding
import com.example.chat_app.ui.home.HomeScreen
import com.example.chat_app.ui.register.Regestier
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : baseActivity<ActivityLoginBinding, LoginViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vmm = vm
        vm.navigator = this

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getVMClass(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun openHomeScreen() {
        val intent=Intent(this, HomeScreen::class.java)
        startActivity(intent)
    }

    override fun openRegesterationScreen() {
        val intent = Intent(this, Regestier::class.java)
        startActivity(intent)
    }
}