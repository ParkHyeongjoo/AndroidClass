package com.example.threaddo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var tvTimer: TextView
    lateinit var tvTimer2: TextView
    val rd = Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById(R.id.tvTimer)
        tvTimer2 = findViewById(R.id.tvTimer2)

        val thread = TimerThread(tvTimer)
        thread.start() // Thread class 안의 run()을 실행함

        val thread2 = TimerThread(tvTimer2)
        thread2.start() // Thread class 안의 run()을 실행함

    }

    //    Main Thread Queue(작업 영역)에 작업을 추가해주는 Handler를 만들자
    val handler = object : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val time = msg.arg1
            val tv = msg.obj as TextView
//            Main UI를 건드는 작업!
//            직접적으로 view 정보를 수정하는게 아니고 메인 작업 Queue 에 '이 작업 해주세요...'라고 작업을 추가!
            tv.text = time.toString()
        }
    }

    //    시간 관련 Thread class 생성
    inner class TimerThread(val tv: TextView) : Thread() {

        //  run() 메소드가 존재
        //  run() : Thread 를 동작시키면 실행되는 메소드
        override fun run() {
            super.run()

            //  10 -> 0 (1초마다 1씩 감소)
            //  sleep(밀리초) 코드를 지연시킴
            for (i in 10 downTo 0) {
                Log.d("timer", i.toString())

//                handler 에게 정보를 전달해주는 객체
                val message = Message()
//                member 변수(field)가 3개 존재
//                arg1 : int, arg2 : int, obj : Any
                message.arg1 = i
                message.obj = tv

                handler.sendMessage(message)

                val rdValue = rd.nextInt(3000)

                Thread.sleep(rdValue.toLong())
            }
        }
    }
}