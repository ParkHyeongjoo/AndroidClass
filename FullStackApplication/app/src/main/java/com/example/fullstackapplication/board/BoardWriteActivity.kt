package com.example.fullstackapplication.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.fullstackapplication.R

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val etBoardTitle = findViewById<EditText>(R.id.etBoardTitle)
        val etBoardContent = findViewById<EditText>(R.id.etBoardContent)
        val imgBoardAdd = findViewById<ImageView>(R.id.imgBoardAdd)
        val imgBoardWrite = findViewById<ImageView>(R.id.imgBoardWrite)

        imgBoardAdd.setOnClickListener {

        }

        imgBoardWrite.setOnClickListener {
            val title = etBoardTitle.text.toString()
            val content = etBoardContent.text.toString()


        }
    }
}