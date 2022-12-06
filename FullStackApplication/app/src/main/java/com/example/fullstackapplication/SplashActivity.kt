package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.fullstackapplication.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        OnCreate 가 실행되고 나서 3초뒤에 IntroActivity 로 넘어가는 코드 작성
//        Thread, Intent
//        프로그램에는 MainThread 무조건 한개
//        내가 만든 작업처리 SubThread
//        SubThread 가 동작하고 있는 MainThread 에 끼어들려면
//        Handler 가 필요하다 (메세지 객체, Thread 를 실행 시킬 수 있음)

//        Handler 를 가져와 주자
//        postDelayed({실행할 코드}, 지연시킬 시간)
        Handler().postDelayed({
//            Intent 생성
            val intent = Intent(
                this@SplashActivity,
                IntroActivity::class.java
            )
//            Intent 실행
            startActivity(intent)
        }, 3000)
        finish()
    }
}