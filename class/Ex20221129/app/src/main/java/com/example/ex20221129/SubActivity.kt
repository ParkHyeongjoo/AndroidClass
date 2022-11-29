package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
//        MainActivity에서 requestCode와 함께 Intent로 이동한 상태

        val btnSend = findViewById<Button>(R.id.btnSend)
        val etResult = findViewById<EditText>(R.id.etResult)

        btnSend.setOnClickListener {
//            1. EditText에 적혀있는 문구를 가져온다
//            2. 문구를 변수에 저장한다
            val str = etResult.text.toString()
//            3. Intent 생성한다
            val intent = Intent()
//            4. Intent에 데이터(문구)를 붙혀준다 ---> putExtra(key, value)
            intent.putExtra("content", str)
//            5. setResult(resultCode, intent)
//              : resultCode : 결과값의 상태를 Main에서 판한다기 위한 (신호)코드
            setResult(RESULT_OK, intent)
//            6. finish()
            finish()

        }
    }
}