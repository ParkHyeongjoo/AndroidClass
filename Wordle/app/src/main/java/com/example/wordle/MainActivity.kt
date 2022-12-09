package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 구글 애드몹
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


//        RecyclerView 사용 6단계
//        1. Container 결정
        val rvGame = findViewById<RecyclerView>(R.id.rvGame)

//        2. Template 결정
//        game_list.xml

//        3. Item 결정
        val gameList = ArrayList<GameVO>()
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))

//        4. Adapter 결정
        val adapter = GameAdapter(this, gameList, "apple")

//        5. Adapter 와 Container 연결
        rvGame.adapter = adapter
        rvGame.layoutManager = LinearLayoutManager(this)

//        6. Event 처리

    }
}