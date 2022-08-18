package com.example.chat_app.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.dataBase.DataBaseUtils
import com.example.chat_app.dataBase.Message
import com.example.chat_app.databinding.RecivedMessageItemBinding
import com.example.chat_app.databinding.SentMessageItemBinding

class ChatAdaptor():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val SEND=1
    val RECIVED=2
     var item= mutableListOf<Message>()
    fun setData(message: MutableList<Message>){
        item.addAll(message)
        notifyItemRangeInserted(item.size+1,message.size)
    }
    override fun getItemViewType(position: Int): Int {
        val item=item[position]
        return if(item.senderId==DataBaseUtils.user?.id){
            SEND
        } else RECIVED

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==SEND){
            val view:SentMessageItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.sent_message_item,parent,false
                )
            return sendMessageViewHolder(view)

        }
            val view:RecivedMessageItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recived_message_item,parent,false
            )
            return recivedMessageViewHolder(view)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is sendMessageViewHolder){
            holder.bind(item[position])
        }else if (holder is recivedMessageViewHolder)
            holder.bind(item[position])

    }

    override fun getItemCount(): Int =item.size

    class sendMessageViewHolder(val send: SentMessageItemBinding) :
        RecyclerView.ViewHolder(send.root) {
        fun bind(message: Message) {
            send.item = message
            send.executePendingBindings()
        }
    }
    class recivedMessageViewHolder(val recived: RecivedMessageItemBinding) :
        RecyclerView.ViewHolder(recived.root) {
        fun bind(message: Message) {
            recived.item = message
            recived.executePendingBindings()
        }

    }

}