package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

//        1. Container 결정
        val rvColor = findViewById<RecyclerView>(R.id.rvColor)

//        2. Template 결정
//        color_list.xml

//        3. Item 결정
        val colorList = ArrayList<ColorVO>()

//        "#" + red(16) + green(16) + blue(16)
        for(i in 255 downTo 0 step 32){
            var red:String = Integer.toHexString(i)
            for(j in 255 downTo 0 step 8){
                var green: String = Integer.toHexString(j)
                for(k in 0..255 step 16){
                    var blue: String = Integer.toHexString(k)

                    if(red.length == 1) red = "0" + red
                    if(green.length == 1) green = "0" + green
                    if(blue.length == 1) blue = "0" + blue

                    colorList.add(ColorVO("#$red$green$blue"))
                }
            }
        }

//        4. Adapter 결정
        val adapter = ColorAdapter(
            this,
            colorList
        )

//        5. Adapter 연결
        rvColor.adapter = adapter
        rvColor.layoutManager = GridLayoutManager(this, 40)
    }
}