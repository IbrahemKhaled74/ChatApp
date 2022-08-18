package com.example.chat_app.register
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.base.baseActivity
import com.example.chat_app.R
import com.example.chat_app.databinding.RegestierActivityBinding
import com.example.chat_app.login.Login

class Regestier : baseActivity<RegestierActivityBinding, RegisterViewModel>(),navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vmm=vm
        vm.navigator=this

    }

    override fun getLayoutId(): Int {
        return R.layout.regestier_activity
    }

    override fun getVMClass(): RegisterViewModel {
       return ViewModelProvider(this)[RegisterViewModel::class.java]
    }



    override fun openLoginScreen() {
        val intent=Intent(this,Login::class.java)
        startActivity(intent)
    }


}