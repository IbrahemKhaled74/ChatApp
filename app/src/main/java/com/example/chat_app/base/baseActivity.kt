package com.example.chat_app.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class baseActivity<DataBinding:ViewDataBinding,VM: baseViewModel<*>>:AppCompatActivity() {
lateinit var dataBinding: DataBinding
lateinit var vm: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding=DataBindingUtil.setContentView(this,getLayoutId())
        vm=getVMClass()
        subscribeToLiveData()

    }

     fun subscribeToLiveData(){
        vm.messageLiveData.observe(this,  {
            showDialogg(it,"ok",
                { p0, p1 -> p0?.dismiss() })
        })
        vm.loading.observe(this,{
                loading->
            if (loading){
                showLoading()
            }else{
                dismissLoading()
            }
        })
         vm.toastMessage.observe(this,{
             Toast.makeText(this,it,Toast.LENGTH_LONG)
         })

    }

    abstract fun getLayoutId():Int
    abstract fun getVMClass():VM
    var loading:ProgressDialog?=null

    fun showLoading(){
        loading= ProgressDialog(this)
        loading?.setMessage("Loading")
        loading?.show()
    }
    fun dismissLoading(){
        loading?.dismiss()
        loading= null

    }
    fun showDialogg(
        message:String?=null,
        posButtonName:String?=null,
        posButtonAction: DialogInterface.OnClickListener?=null,
        negButtonName:String?=null,
        negButtonAction: DialogInterface.OnClickListener?=null,
    )
    {
        val dialog= AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(posButtonName,posButtonAction)
            .setNegativeButton(negButtonName,negButtonAction)
        dialog.show()
    }

}