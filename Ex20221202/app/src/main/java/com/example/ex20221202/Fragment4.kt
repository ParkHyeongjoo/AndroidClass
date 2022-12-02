package com.example.ex20221202

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class Fragment4 : Fragment() {

//    1. 서버에 저장
//    2. SQLite
//    3. FireBase : 회원가입, 로그인, ... 세부적인기능들이 이미 다 구현되어 있음
//    4. SharedPreference
//      - SQLite보다 가벼운
//      - Fragment간에 데이터 전송
//      - 어플리케이션 첫 실행 감지

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_4, container, false)

        val etUrl = view.findViewById<EditText>(R.id.etUrl)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

//        SharedPreference 에 Url 값 저장하기
//        1. SharedPreference 가져오기
//        1) 이름 지정
//        2) 모드 설정
        val sharedPreferences = requireActivity().getSharedPreferences("sp1",Context.MODE_PRIVATE)

//        2. SharedPreference에 데이터를 저장할 수 있는 editor 가져오기

        val editor = sharedPreferences?.edit()


//        3. editor 를 통해서 데이터 저장하기 (commit)
        btnSend.setOnClickListener {
        val url = etUrl.text.toString()

            editor?.putString("url", url)
            editor?.commit()
//            (context as Activity).finish()
        }

        return view
    }
}