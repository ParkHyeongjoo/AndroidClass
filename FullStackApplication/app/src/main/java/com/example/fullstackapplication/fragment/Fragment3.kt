package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardAdapter
import com.example.fullstackapplication.board.BoardInsideActivity
import com.example.fullstackapplication.board.BoardVO
import com.example.fullstackapplication.board.BoardWriteActivity
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Fragment3 : Fragment() {

    //    getBoardData를 통해 받아온 item(BoardVO)을 관리하는 배열
    var boardList = ArrayList<BoardVO>()
    var keyData = ArrayList<String>()

    lateinit var adapter: BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_3, container, false)

        val btnWrite = view.findViewById<Button>(R.id.btnWrite)
        val rvBoard = view.findViewById<RecyclerView>(R.id.rvBoard)

        getBoardData()

        adapter = BoardAdapter(
            requireContext(),
            boardList
        )

        adapter.setOnItemClickListener(object: BoardAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(requireContext(),
                BoardInsideActivity::class.java)

                intent.putExtra("title", boardList[position].title)
                intent.putExtra("content", boardList[position].content)
                intent.putExtra("time", boardList[position].time)
                intent.putExtra("key", keyData[position])

                startActivity(intent)
            }
        })

        rvBoard.adapter = adapter
        rvBoard.layoutManager = LinearLayoutManager(requireContext())

        btnWrite.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    //    board 에 있는 데이터 다 가져오는 함수
    fun getBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                Firebase 에서 snapshot 으로 데이터를 받아 온 경우
//                게시물의 uid
//                  - BoardVO
                boardList.clear()
                for (model in snapshot.children) {
                    val item = model.getValue(BoardVO::class.java)
                    if (item != null) {
                        boardList.add(item)
                    }
                    keyData.add(model.key.toString())
                }
                boardList.reverse()
                keyData.reverse()
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
//                오류가 발생했을 경우 실행되는 함수

            }
        }
//        board에 있는 모든 데이터가 들어간다
        FBdatabase.getBoardRef().addValueEventListener(postListener)
    }

}