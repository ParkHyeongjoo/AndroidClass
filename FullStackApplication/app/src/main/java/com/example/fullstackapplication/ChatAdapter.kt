package com.example.fullstackapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(val context: Context, val loginId: String, val chatList: ArrayList<ChatVO>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgChat: ImageView
        val tvChatOtherName: TextView
        val tvChatOtherMsg: TextView
        val tvChatMyMsg: TextView

        init {
            imgChat = itemView.findViewById(R.id.imgChat)
            tvChatOtherName = itemView.findViewById(R.id.tvChatOtherName)
            tvChatOtherMsg = itemView.findViewById(R.id.tvChatOtherMsg)
            tvChatMyMsg = itemView.findViewById(R.id.tvChatMyMsg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.chat_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = chatList[position].name

        if(loginId == name){
            holder.imgChat.visibility = View.INVISIBLE
            holder.tvChatOtherName.visibility = View.INVISIBLE
            holder.tvChatOtherMsg.visibility = View.INVISIBLE

            holder.tvChatMyMsg.text = chatList[position].msg
            holder.tvChatMyMsg.visibility = View.VISIBLE
        }else{
            holder.imgChat.setImageResource(R.drawable.profile)
            holder.imgChat.visibility = View.VISIBLE
            holder.tvChatOtherName.text = chatList[position].name
            holder.tvChatOtherName.visibility = View.VISIBLE
            holder.tvChatOtherMsg.text = chatList[position].msg
            holder.tvChatOtherMsg.visibility = View.VISIBLE

            holder.tvChatMyMsg.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}