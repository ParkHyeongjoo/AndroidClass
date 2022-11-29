package com.example.ex20221129

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class BoardActivity : AppCompatActivity() {

    lateinit var btnWrite: Button
    lateinit var tvContent: TextView
    lateinit var btnBoard: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        val btnLogIn = findViewById<Button>(R.id.btnLogin1)
        btnWrite = findViewById(R.id.btnWrite)
        val lv = findViewById<ListView>(R.id.lvLunch)
        tvContent = findViewById(R.id.tvContent)
        val etBoard = findViewById<EditText>(R.id.etBoard)
        btnBoard = findViewById(R.id.btnBoard)

        btnLogIn.setOnClickListener {

            val intent = Intent(this@BoardActivity, LoginActivity::class.java)

            launcher.launch(intent)
        }

/*              lv.setOnItemClickListener { adapterView, view, i, l ->
//            adapterView : ListView에 대한 정보
//            view : ListView가 있는 현재 페이지에 대한 정보
//            i (position) -> 내가 클릭한 item위치 (index 0 ~
//            l (id)long -> 내가 클릭한 item의 고유한 값
            if (adapterView != null) {
                Toast.makeText(
                    this,
                    adapterView.getItemAtPosition(i).toString(),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }*/

//        Adapter View를 사용하기 위한 6단계
//        1. Container 결정
//        : ListView 의 위치를 결정

//        2. Template 결정
//        : 각 항목(Item)에 적용될 디자인을 결정
//        : Layout 패키지에 xml 형태로 생성
//        --> board_list.xml 최상위 레이아웃 : TextView , 이 때 id는 tvBoard

//        3. Item 결정
        val data = ArrayList<String>()
        data.add("1. 나는 아무 생각이 없다")
        data.add("2. 왜냐하면")
        data.add("3. 아무 생각이 없기 때문이다")

//        4. Adapter 결정
//        Adapter : 디자인(항목 뷰, 템플릿) + Item 결합해서
//        Adapter View 에 동적으로 뿌려주는 역할

//        ArrayAdapter 를 사용
//        조건) 템플릿이 TextView, 아이템 요소가 String
//        생성자의 요소 4개
//        1) 페이지 정보
//        2) 템플릿(항목 뷰)
//        3) TextView 의 id
//        4) Item
        val adapter = ArrayAdapter<String>(
            this,
            R.layout.board_list,
            R.id.tvMenu,
            data
        )

//        5. Container에 Adapter 부착
        lv.adapter = adapter

//        6. Event 처리
        var index = 4
        btnBoard.setOnClickListener {
            val input = etBoard.text.toString()

            data.add("$index. $input")
            index++

//            adapter를 새로고침하자!
            adapter.notifyDataSetChanged()
            etBoard.text = null
        }

        lv.setOnItemClickListener { adapterView, view, position, l ->
//            AlertDialog 옵션 정보를 담는 builder 생성!
            val builder = AlertDialog.Builder(this)
            builder.setTitle("게시글 삭제")
            builder.setMessage("정말 삭제 하시겠습니까?")
            builder.setPositiveButton("삭제",
                DialogInterface.OnClickListener { dialogInterface, id ->
                    data.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                )
            builder.setNegativeButton("취소", null)

//            주의!
//            builder를 통해 옵션을 단 이후
//            맨 마지막에는 무조건 ! show() 함수를 달아야 한다

            builder.show()
        }
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == RESULT_OK) {
            btnWrite.isEnabled = true
            btnBoard.isEnabled = true
            tvContent.text = it.data?.getStringExtra("id") + " 님 환영합니다"
        } else {
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
        }
    }
}