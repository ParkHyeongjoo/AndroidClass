package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        1. View들의 id값을 찾아오자 (finViewById)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPw = findViewById<TextInputEditText>(R.id.etPw)
        val btnLogIn = findViewById<Button>(R.id.btnLogIn)

//        2. Button에 Event 달아주기 (setOnClickListener)
//        2-1. EditText에 적혀있는 email, password값을 가져오기
//        (email, pw : 변수) ---> 문자열로 형변환
//        2-2. 가져온 email, pw가 smhrd@smhrd.or.kr, qwer1234 가 맞는지 확인(조건식)
//        맞다면 Toast로 "로그인 성공"
//        틀리면 Toast로 "로그인 실패"

        btnLogIn.setOnClickListener {
            var checkEmail = etEmail.text.toString()
            var checkPw = etPw.text.toString()

            if(checkEmail=="smhrd@smhrd.or.kr" && checkPw=="qwer1234"){
                Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }

    }
}