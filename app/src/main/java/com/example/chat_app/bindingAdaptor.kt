package com.example.chat_app

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
@BindingAdapter("app:error")
fun error(text:TextInputLayout,error:String?){
    text.error=error
}
@BindingAdapter("app:add_image")
fun addImage(imageView: ImageView,imageId:Int){
    imageView.setImageResource(imageId)
}
