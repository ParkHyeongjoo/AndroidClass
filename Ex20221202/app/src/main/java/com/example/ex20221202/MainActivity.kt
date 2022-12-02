package com.example.ex20221202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvWeather = findViewById<RecyclerView>(R.id.rvWeather)
        val btnWeather = findViewById<Button>(R.id.btnWeather)

        var cityList = ArrayList<String>()
        cityList.add("GwangJu")
        cityList.add("Seoul")
        cityList.add("Osaka")
        cityList.add("Hokkaido")
        cityList.add("London")
        cityList.add("Miami")

        var weatherList = ArrayList<WeatherVO>()

//        Volley 활용한 네트워크 통신
//        1. Volley 라이브러리 설정
//          - 라이브러리 추가, 인터넷 권한, http 열기
//        2. RequestQueue 생성
        val requestQueue = Volley.newRequestQueue(this)

        val adapter = WeatherAdapter(this, weatherList)

        rvWeather.adapter = adapter
        rvWeather.layoutManager = LinearLayoutManager(this)

//        3. Request 생성
        btnWeather.setOnClickListener {

            val requestList = ArrayList<StringRequest>()
            for (i in 0 until cityList.size) {

                val url: String =
                    "https://api.openweathermap.org/data/2.5/weather?q=${cityList[i]}&appid=3ebab122da06164dd46ce2c31e0a4c91&units=metric"
                val request = StringRequest(
                    Request.Method.GET,
                    url,
                    { response ->
                        Log.d("날씨$i : ", response)

                        val result = JSONObject(response)
                        val weathers = result.getJSONArray("weather")
                        val weather = weathers[0] as JSONObject
                        val state = weather.getString("main")
                        val main = result.getJSONObject("main")
                        val temp = main.getString("temp")
                        val humidity = main.getString("humidity")

                        val wind = result.getJSONObject("wind")
                        val speed = wind.getString("speed")

                        Log.d("현재 날씨$i", "상태 : $state , 온도 : $temp , 습도 : $humidity , 풍속 : $speed")
                        weatherList.add(WeatherVO(cityList[i], state, temp, humidity, speed))
                        adapter.notifyDataSetChanged()
                    },
                    { error -> }
                )
                requestList.add(request)
            }
            for (i in 0 until requestList.size) {
                requestQueue.add(requestList[i])
            }
        }
    }
}