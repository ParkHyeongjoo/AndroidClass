package com.example.ex20221202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

//        View들의 Id값 찾아오기
        val btnLog = findViewById<Button>(R.id.btnLog)
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)
        supportFragmentManager.beginTransaction().replace(
//                        1) fragment 가 들어갈 위치
            R.id.fl,
//                        2) 내가 갈아끼울 fragment 객체
            Fragment1()
        ).commit()

//        Fragment 구현
//        bnv에서 선택한 메뉴에 따라 fl 에 표시할 Fragment 를 변경

        bnv.setOnItemSelectedListener {
//            item : 내가 선택한 메뉴의 정보
            Log.d("id", it.itemId.toString())
            when (it.itemId) {
                R.id.tab1 -> {
                    Toast.makeText(this, "첫번째 탭입니다", Toast.LENGTH_SHORT).show()
//                    BeginTransaction() : Fragment 의 추가 / 변경 / 삭제
                    supportFragmentManager.beginTransaction().replace(
//                        1) fragment 가 들어갈 위치
                        R.id.fl,
//                        2) 내가 갈아끼울 fragment 객체
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 -> {
                    Toast.makeText(this, "두번째 탭입니다", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(
//                        1) fragment 가 들어갈 위치
                        R.id.fl,
//                        2) 내가 갈아끼울 fragment 객체
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 -> {
                    Toast.makeText(this, "세번째 탭입니다", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(
//                        1) fragment 가 들어갈 위치
                        R.id.fl,
//                        2) 내가 갈아끼울 fragment 객체
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 -> {
                    Toast.makeText(this, "네번째 탭입니다", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(
//                        1) fragment 가 들어갈 위치
                        R.id.fl,
//                        2) 내가 갈아끼울 fragment 객체
                        Fragment4()
                    ).commit()
                }
            }
            true
//            false : 이벤트가 끝나지 않음
//            클릭 감지는 하지만 이동을 하지 않음
//            true : 이벤트 종료를 감지하고 이동을 함
        }
    }
}