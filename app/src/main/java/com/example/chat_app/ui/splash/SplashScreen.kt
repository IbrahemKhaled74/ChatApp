package com.example.chat_app.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.databinding.SplashBinding
import com.example.chat_app.ui.home.HomeScreen
import com.example.chat_app.ui.login.Login
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreen
    : baseActivity<SplashBinding, SplashViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.navigator = this

        lifecycleScope.launch {
            vm.checkUser()
        }
    }

    override fun startLoginActivity() {

        val intent = Intent(this, Login::class.java)
        startActivity(intent)

    }

    override fun goToHomeActivity() {

        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)

    }

    override fun getLayoutId(): Int {
        return R.layout.splash
    }

    override fun getVMClass(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }
}