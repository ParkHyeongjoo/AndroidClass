package com.example.ex20221124

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// : Kotlin에서 상속
class ConstraintActivity : AppCompatActivity() {

    //    전역변수(뷰)로 만들기
    //    뷰에 대해서는 선언만 하는 거는 불가능 초기화가 꼭 이루어져야 한다
    //    lateinit 이라는 키워드로 나중에 꼭 초기화를 하겠다. 라는 약속을 할 수 있다
    lateinit var tvResult: TextView
    lateinit var etNum1: EditText
    lateinit var etNum2: EditText

    // onCreate()는 Activity가 실행될 때 최초 딱 한번(가장 먼저) 호출되는 메서드
    // : Activity 생명주기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        **** xml이랑 ktClass랑 연결하는 코드 없으면 화면 안뜸 ****
        setContentView(R.layout.activity_constraint)

//        1. xml의 View에 id를 지정
//        2. id값을 이용해서 view를 찾아온다 (findViewById)

//        id 값은 문자열로 정해줬는데 받아오는값이 Int
//        R 폴더에 모든 뷰(리소스)들의 id값이 저장이 되는데 주소값이 저장
//        16진수 상수형태로 저장이 되어있다 (Int)

//        ** setContentview 위로 find 할 수 없다!! **
        tvResult = findViewById(R.id.tvResult)
        tvResult.setTextColor(Color.BLUE)
        tvResult.setTextColor(Color.parseColor("#e169ff"))
        tvResult.textSize = 40.0f
        tvResult.text = "안녕하세요!"

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)

        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)

//        CharSequence 인터페이스 - String은 CharSequence 인터페이스 상속받는

//        더하기 버튼을 눌렀을 때 "더하기 버튼이 눌렸습니다"라는 Toast를 띄워 주자!

//        이벤트를 주는 방법
//        1) 이벤트 메소드 설계 후 뷰에 연결하기 (onCreate 밖에 설계)
//        2) innerClass (Listener OnClick 구현)
        btnMinus.setOnClickListener {
//            {}안에다가 기능 구현만 하면 됨 !
//            "빼기 버튼이 눌렸습니다" 토스트 띄우기

//            Toast.makeText(this,"빼기 버튼이 눌렸습니다",Toast.LENGTH_SHORT).show()

//            1. EditText에 적혀있는
//            getText --> Editable --> 문자열로 형변환 --> 정수형
//            2. num1, num2 연산 결과를 tvResult에 set해주세요!
            var num1 = etNum1.text.toString().toInt()
            var num2 = etNum2.text.toString().toInt()
            tvResult.text = "연산 결과 : ${num1-num2}"
        }

//        3) interface 상속받게 만들어서 OnClick 구현
    }

    //    onCreate 밖에 이벤트 메소드 설계
//    이벤트 리스너는 무조건 View 매개변수를 가지고 있어야 한다.
    fun btnClick(v: View) {
//        Toast 띄우기 !
//    1) this, ConstraintActivity.this : Toast를 띄울 화면 정보
//    2) 문구 (String만 가능, Int가 허용되는 경우는 id값만)
//    3) Toast에 Short(3초), Long(5초) 속성 사용

//        Toast.makeText(this,"더하기 버튼이 눌렸습니다",Toast.LENGTH_SHORT).show()
        var num1 = etNum1.text.toString().toInt()
        var num2 = etNum2.text.toString().toInt()
//        Emulator를 처음 실행시키면 EditText에는 아무 값도 없음 ""
//        "".toInt() NumberFormatException
//        버튼을 눌렀을 때 적혀있는 값을 가지고 와줘야 함!!
        var result =  num1 + num2
        tvResult.text = "연산결과 : $result"
    }
}