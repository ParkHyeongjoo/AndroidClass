package com.example.ex20221130_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        AdapterView
//        - RecyclerView
//        : ListView의 개선판 : ViewHolder를 강제 !

//        - layoutManager : 유연하다
//        -> Linear : 수직으로 쌓기
//        -> Grid
//        -> StraggleGrid

//        AdapterView 6단계
//        1) Container 결정
//        : MainActivity - RecyclerView
        val rvPoke = findViewById<RecyclerView>(R.id.rvPoke)

//        2) Template 결정
//        : poke_list.xml

//        3) Item 결정
        val pokeList = ArrayList<PokeVO>()
        pokeList.add(PokeVO(R.drawable.p1, "피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2, "꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3, "파이리", "풀"))
        pokeList.add(PokeVO(R.drawable.p4, "이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5, "버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6, "구구", "비행"))
        pokeList.add(PokeVO(R.drawable.p1, "피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2, "꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3, "파이리", "풀"))
        pokeList.add(PokeVO(R.drawable.p4, "이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5, "버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6, "구구", "비행"))
        pokeList.add(PokeVO(R.drawable.p1, "피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2, "꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3, "파이리", "풀"))
        pokeList.add(PokeVO(R.drawable.p4, "이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5, "버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6, "구구", "비행"))

//        4) Adapter 결정
        val adapter = PokeAdapter2(
            this@MainActivity2,
            pokeList
        )

//        5) Adapter 연결
        rvPoke.adapter = adapter
//        5-2) 내가 구현하고 하는 모양에 맞는
//        layoutManager 를 결정
//        rvPoke.layoutManager = LinearLayoutManager(this)
//        Grid 쌓기
        rvPoke.layoutManager = GridLayoutManager(this, 2)

    }
}