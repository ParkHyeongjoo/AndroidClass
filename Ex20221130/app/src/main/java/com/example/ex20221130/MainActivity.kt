package com.example.ex20221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var phoneList = ArrayList<PhoneVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        1. Container 정하기
//        : 화면에서 ListView의 위치 정해주기(xml파일)

//        2. ListView 한 칸에 들어갈 디자인 정해주기 (xml파일, layout 폴더에 생성)

//        3. ListView 에 들어갈 데이터 만들기 -> 하나의 자료형 (PhoneVO)
//        이미지뷰에 들어갈 Image의 ID값 (Int)
//        이름, 전화번호 (String)
        val p1 = PhoneVO(R.drawable.chalie, "찰리 푸스", "010-1234-5678")
        val p2 = PhoneVO(R.drawable.kalina, "카리나", "010-7777-6481")
        val p3 = PhoneVO(R.drawable.kazuha, "카즈하", "010-1375-3768")
        val p4 = PhoneVO(R.drawable.suhwa, "예슈화", "010-1834-8416")
        phoneList.add(p1)
        phoneList.add(p2)
        phoneList.add(p3)
        phoneList.add(p4)
        phoneList.add(PhoneVO(R.drawable.winter, "카즈하", "010-6789-1678"))

//        4. Adapter 만들기
//        데이터의 자료형이 내가만든 자료형(VO)이기 때문에 안드로이드에서 기본적으로 제공하는 ArrayAdapter는 사용이 불가능
//        Adapter : data 와 template 을 합쳐서 ListView에 적재시켜주는 역할
        val adapter = PhoneAdapter(
            this,
            R.layout.phone_list,
            phoneList
        )

//        5. ListView 에 Adapter 연결

//        6. Event 달아주기
    }
}