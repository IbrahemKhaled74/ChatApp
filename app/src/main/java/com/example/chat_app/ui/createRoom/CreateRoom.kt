package com.example.chat_app.ui.createRoom

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.base.baseActivity
import com.example.chat_app.databinding.ActivityCreateRoomBinding
import com.example.chat_app.ui.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateRoom : baseActivity<ActivityCreateRoomBinding, CreateRoomViewModel>(), Navigator {
    @Inject
    lateinit var spinner: SpinnerAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vmm = vm
        vm.navigator = this
        initRecycleView()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_create_room
    }

    fun initRecycleView() {
        spinner = SpinnerAdaptor(vm.categoryList)
        dataBinding.spinner.adapter = spinner
        dataBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                vm.selectedCategory = vm.categoryList[pos]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

    }

    override fun goToHomeScreen() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)

    }


    override fun getVMClass(): CreateRoomViewModel {
        return ViewModelProvider(this)[CreateRoomViewModel::class.java]
    }


}