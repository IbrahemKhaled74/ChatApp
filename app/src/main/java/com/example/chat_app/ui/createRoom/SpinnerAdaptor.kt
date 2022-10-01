package com.example.chat_app.ui.createRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.chat_app.R
import com.example.chat_app.databinding.SpinnerItemBinding
import com.example.domain.models.Category

class SpinnerAdaptor(val items: List<Category>?) : BaseAdapter() {
    override fun getCount(): Int = items?.size ?: 0

    override fun getItem(index: Int): Any = items?.get(index)!!

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(index: Int, view: View?, continer: ViewGroup?): View {
        var myView = view
        var viewHolder: viewHolder
        if (myView == null) {
            var view: SpinnerItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(continer?.context),
                R.layout.spinner_item, continer, false
            )
            myView = view.root
            viewHolder = viewHolder(view)
            myView.tag = viewHolder


        } else {
            viewHolder = myView.tag as viewHolder
        }
        items?.get(index)?.let { viewHolder.bind(it) }
        return myView


    }

    class viewHolder(val item: SpinnerItemBinding) {
        fun bind(room: Category) {
            item.item = room
            item.invalidateAll()
        }
    }
}