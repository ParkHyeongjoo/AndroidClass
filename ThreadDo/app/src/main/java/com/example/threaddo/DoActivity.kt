package com.example.threaddo

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvScore = findViewById<TextView>(R.id.tvScore)

        val imageViews = ArrayList<ImageView>()

        for (i in 1..9) {
            val resId = resources.getIdentifier("imageView$i", "id", packageName)
            val imageView = findViewById<ImageView>(resId)
            imageViews.add(imageView)
            imageView.visibility = View.INVISIBLE
        }

        btnStart.setOnClickListener {
            for (i in 0 until imageViews.size) {
                val imageView = imageViews[i]
                imageView.visibility = View.VISIBLE

                val thread = DoThread(imageView)
                thread.start()
            }
        }
    }

    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val imageView = msg.obj as ImageView
            imageView.setImageResource(R.drawable.on)
        }
    }

    inner class DoThread(val imageView : ImageView) : Thread(){
        override fun run() {
            super.run()

//            랜덤하게 0 ~ 5초 후에 일어나게
            val onTime = rd.nextInt(5000)

            Thread.sleep(onTime.toLong())

            val message = Message()
            message.obj = imageView
            handler.sendMessage(message)
        }
    }

}