package com.example.threaddo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    val rd = Random
    var time = 0
    var timerTask: Timer? = null
    lateinit var btnGame: Button
    lateinit var tv1to25Time: TextView
    lateinit var tvTop: TextView
    var cnt: Int = 1
    var timeTop = 99999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnGame = findViewById(R.id.btnGame)
        tv1to25Time = findViewById(R.id.tv1to25Time)
        tvTop = findViewById(R.id.tvTop)

        val numbers = ArrayList<Int>()

        for (i in 1..25) {
            numbers.add(i)
        }

        Log.d("랜덤", numbers.toString())

        val btns = ArrayList<Button>()

        for (i in 1..25) {
            val resId = resources.getIdentifier("btn$i", "id", packageName)
            val btn = findViewById<Button>(resId)
            btns.add(btn)
            btn.visibility = View.INVISIBLE
        }

        btnGame.setOnClickListener {

            for (i in 0 until 100) {
                val rdNum1 = rd.nextInt(25)
                val rdNum2 = rd.nextInt(25)

                val temp = numbers[rdNum1]
                numbers[rdNum1] = numbers[rdNum2]
                numbers[rdNum2] = temp
            }

            cnt = 1
            start()

            for (i in 0 until btns.size) {
                val btn = btns[i]
                btn.text = numbers[i].toString()
                btn.visibility = View.VISIBLE

                btn.setOnClickListener {
                    if (btn.text.toString().toInt() == cnt) {
                        btn.visibility = View.INVISIBLE
                        cnt++
                        Log.d("카운트", cnt.toString())
                        if (cnt == 26) stop()
                    }
                }
            }
        }
    }

    fun start() {
        time = 0
        tv1to25Time.text = "00 : 00"
        timerTask = timer(period = 10) {
            time++
            var sec = (time / 100).toString()
            var milli = (time % 100).toString()

            if (sec.toInt() < 10) sec = "0$sec"
            if (milli.toInt() < 10) milli = "0$milli"

            runOnUiThread {
                tv1to25Time.text = "$sec : $milli"
            }
        }
    }

    fun stop() {
        if(timeTop > time){
            timeTop = time
            var sec = (time / 100).toString()
            var milli = (time % 100).toString()
            if (sec.toInt() < 10) sec = "0$sec"
            if (milli.toInt() < 10) milli = "0$milli"
            tvTop.text = "$sec : $milli"
        }
        timerTask?.cancel()
    }
}