package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.fullstackapplication.auth.IntroActivity
import com.example.fullstackapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)

        imgLogout.setOnClickListener {
            auth.signOut()
//            로그아웃 하고 나면 IntroActivity로 이동
            val intent = Intent(this@MainActivity, IntroActivity::class.java)
//            이전에 쌓여있는 Activity를 모두 날려주기
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

//        auth에 담겨있는 기능
//        createUserWithEmailAndPassword : 회원가입 (email, pw)
//        signInWithEmailAndPassword : 회원 로그인 (email, pw)
//        signInAnonymously : 익명 로그인
//        signOut : UID 삭제


        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            Fragment1()
//            ContactFragment()
        ).commit()

        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tab1 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
//                        ContactFragment()
                    ).commit()
                }
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
                R.id.tab5 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
//                        Fragment5()
                        ChatFragment()
                    ).commit()
                }
            }
            true
        }
    }
}