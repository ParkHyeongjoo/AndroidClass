package com.example.ex20221129

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.random.Random

class LunchActivity : AppCompatActivity() {

    lateinit var tvLunch:TextView
    lateinit var etLunch:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

        val rd = Random

//        Adapter View 6단계
//        1. Container 결정
        val lvLunch = findViewById<ListView>(R.id.lvLunch)
        val btnRandom = findViewById<Button>(R.id.btnRandom)
        tvLunch = findViewById(R.id.tvLunch)
        etLunch = findViewById(R.id.etLunch)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
//        viewBinding 기법 -> 초기화를 해결

//        2. Template 결정
//        lunch_list.xml 사용

//        3. Item 결정
        val data = ArrayList<String>()

//        4. Adapter 결정
        val adapter = ArrayAdapter<String>(
            this,
            R.layout.lunch_list,
            R.id.tvMenu,
            data
        )

//        5. Container 에 Adapter 부착
        lvLunch.adapter = adapter

//        6. Event 처리

        btnAdd.setOnClickListener {
            val input = etLunch.text.toString()

            data.add(input)

            adapter.notifyDataSetChanged()
            etLunch.text = null
        }

        btnRandom.setOnClickListener {
            var menu = rd.nextInt(data.size)

            tvLunch.text = data[menu]
        }

    }
}