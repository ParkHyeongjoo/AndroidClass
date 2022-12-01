package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    //    Volley에 필요한 객체 2개
    var queue: RequestQueue? = null // 요청을 가지고 서버로 이동하는 객체
    lateinit var request: StringRequest // 요청 / 응답이 들어가는 객체
    var movies = ArrayList<MovieVO>() // 영화 정보들이 담길 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val btnMovie = findViewById<Button>(R.id.btnMovie)

//        1. Container 결정
        val rcMovie = findViewById<RecyclerView>(R.id.rcMovie)

//        2. ListView 결정
//        movie_list.xml

//        3. Item 결정
//        var movies = ArrayList<MovieVO>()

//        4. Adapter 결정
        val adapter = MovieAdapter(
            this,
            movies
        )

//        5. Adapter 연결
        rcMovie.adapter = adapter
        rcMovie.layoutManager = LinearLayoutManager(this)

//        6. Event 처리

//        버튼을 클릭하거나, 에뮬레이터를 작동, OnCreate가 실행될때마다 request가 들어갈 장소를 만들고 있음
//        if문으로 처음에만 만들도록 함
        if (queue == null) queue = Volley.newRequestQueue(this@MovieActivity)
//        btnMovie를 클릭했을 때 영화정보를 (response) Log로 확인해보자
        btnMovie.setOnClickListener {
            Log.d("되냐", "1")

            val url =
                "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20221130"

            request = StringRequest(
//                1. get / post
                Request.Method.GET,
//                2. URL
                url,
//                3. 응답 성공시 Listener
                { response ->
//                    응답 받아온 response : String
                    val json1 = JSONObject(response)
//                    Log.d("json1 : ", json1.toString())
                    val json2 = json1.getJSONObject("boxOfficeResult")
                    Log.d("json2 : ", json2.toString())
                    val json3 = json2.getJSONArray("dailyBoxOfficeList")
                    Log.d("json3 : ", json3.toString())

                    /*val movie = json3.getJSONObject(0)
                    val rank = movie.getString("rank")
                    Log.d("rank", rank)*/
                    Log.d("for문", json3.length().toString())

                    for (i in 0 until json3.length()) {
                    Log.d("for문", "2")
                        val movie = json3.getJSONObject(i)
//                        rank
                        var rank = movie.getString("rank")
                        Log.d("for문", rank)
//                        rankOldAndNew
                        var rankOldAndNew = movie.getString("rankOldAndNew")
                        Log.d("for문", rankOldAndNew)
//                        movieNm
                        var movieNm = movie.getString("movieNm")
                        Log.d("for문", movieNm)
//                        audiAcc
                        var audiAcc = movie.getString("audiAcc")
                        Log.d("for문", audiAcc)
//                        openDt
                        var openDt = movie.getString("openDt")
                        Log.d("for문", openDt)

                        movies.add(MovieVO(rank, rankOldAndNew, movieNm, audiAcc, openDt))
                        adapter.notifyDataSetChanged()
                    }

                },
//                4. 응답 실패시
                { error ->
                    Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
                }
            )
            queue?.add(request)

        }
    }
}