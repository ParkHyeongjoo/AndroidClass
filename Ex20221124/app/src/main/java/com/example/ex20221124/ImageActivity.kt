package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class ImageActivity : AppCompatActivity() {

    //    Kotlin 배열 선언 방법
    val imgArr = intArrayOf(
        R.drawable.red,
        R.drawable.blue,
        R.drawable.pink,
        R.drawable.yellow,
        R.drawable.black
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val img = findViewById<ImageView>(R.id.img)
        val btnPre = findViewById<Button>(R.id.btnPre)
        val btnNext = findViewById<Button>(R.id.btnNext)

        var index: Int = 0
        img.setImageResource(imgArr[index])

        btnPre.setOnClickListener {
//            index -1 감소
//            해당 index에 있는 img의 id를 가져와서 Imageview에 set하자
            index--

            if (index < 0) index = imgArr.size - 1

            img.setImageResource(imgArr[index])
        }
        btnNext.setOnClickListener {
            index++

            if (index > imgArr.size - 1) index = 0

            img.setImageResource(imgArr[index])
        }
    }
}