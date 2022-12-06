package com.example.threaddo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class DoActivity : AppCompatActivity() {

    val rd = Random
    var isPlaying: Boolean = false
    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvTime = findViewById<TextView>(R.id.tvTime)

        val imageViews = ArrayList<ImageView>()

        for (i in 1..9) {
            val resId = resources.getIdentifier("imageView$i", "id", packageName)
            val imageView = findViewById<ImageView>(resId)
            imageViews.add(imageView)
            imageView.visibility = View.INVISIBLE
        }

        btnStart.setOnClickListener {
            score = 0
            TimeThread(tvTime).start()
            isPlaying = true

            for (i in 0 until imageViews.size) {
                val imageView = imageViews[i]
                imageView.visibility = View.VISIBLE
//                각 이미지뷰의 tag 는 최초 0이다
//                여기서 0은 두더지가 앉아 있음을 의미한다
                imageView.tag = 0

                val thread = DoThread(imageView)
                thread.start()

                imageView.setOnClickListener {
                    if (imageView.tag == 1) {
                        score++
                        imageView.setImageResource(R.drawable.off)
                        imageView.tag = 0
                    } else {
                        score--
                        if (score < 0) score = 0
                    }
                    tvScore.text = "현재 점수 : $score"
                }
            }
        }
    }

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val imageView = msg.obj as ImageView // 어떤 이미지 뷰에 적용될 것인지 ?
            val img = msg.arg1 // on이미지인지 off이미지인지 (이미지 정보를 담고 있는 리소스)
            val tag = msg.arg2 // 현재 이미지의 상태 ! 1이라면 일어나 있음, 0이라면 앉아 있음
            imageView.setImageResource(img)
            imageView.tag = tag
        }
    }

    inner class DoThread(val imageView: ImageView) : Thread() {
        override fun run() {
            super.run()

            while (isPlaying) {
                var level = score * 20
                val onTime = rd.nextInt(5 * (1000 - level))
                Thread.sleep(onTime.toLong())

                val onMessage = Message()
                onMessage.arg1 = R.drawable.on
                onMessage.arg2 = 1 // 일어난 상태
                onMessage.obj = imageView
                handler.sendMessage(onMessage)

                val offTime = rd.nextInt(3 * (1000 - level))
                Thread.sleep(offTime.toLong())
                val offMessage = Message()
                offMessage.arg1 = R.drawable.off
                offMessage.arg2 = 0 // 앉은 상태
                offMessage.obj = imageView
                handler.sendMessage(offMessage)
            }
        }
    }

    val timeHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val tv = msg.obj as TextView
            val time = msg.arg1

            tv.text = "남은 시간 : $time 초"
        }
    }

    inner class TimeThread(val tv: TextView) : Thread() {
        override fun run() {
            super.run()

            for (i in 30 downTo 0) {
                val message = Message()
                message.obj = tv
                message.arg1 = i
                timeHandler.sendMessage(message)

                Thread.sleep(1000)
            }
            isPlaying = false

/*              //  inner class 에서 다른 액티비티로 이동
            val intent = Intent(this@DoActivity, MainActivity::class.java)
            startActivity(intent)*/
        }
    }

}