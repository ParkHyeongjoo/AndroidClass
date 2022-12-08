package com.example.fullstackapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        val etChat = view.findViewById<EditText>(R.id.etChat)
        val btnChat = view.findViewById<ImageButton>(R.id.btnChat)

        val sp = activity?.getSharedPreferences("loginInfo", Context.MODE_PRIVATE)
        val loginId = sp?.getString("loginId", "null") as String

//        AdapterView 6단계
//        1. Container 결정
        val rvChat = view.findViewById<RecyclerView>(R.id.rvChat)

//        2. Template 결정
//        chat_list.mxl

//        3. Item 결정
//        ChatVO
        val chatList = ArrayList<ChatVO>()

//        4. Adapter 결정
        val adapter = ChatAdapter(
            requireContext(),
            loginId,
            chatList
        )

//        5. Container 에 Adapter 부착
        rvChat.adapter = adapter
        rvChat.layoutManager = LinearLayoutManager(requireContext())

//        6. Event
        val db = Firebase.database
        val chatRef = db.getReference("chat")

        btnChat.setOnClickListener {
            val msg = etChat.text.toString()

//            Firebase RealTime Database 의 chat 경로에 ChatVO Class 를 setValue
            val chat = ChatVO(loginId, msg)

            chatRef.push().setValue(chat)
            etChat.text = null
        }

        chatRef.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatItem = snapshot.getValue<ChatVO>() as ChatVO
                chatList.add(chatItem)
//                추가가 완료된 이후 어댑터 새로고침
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        return view
    }


}