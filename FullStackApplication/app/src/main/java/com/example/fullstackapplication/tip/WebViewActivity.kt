package com.example.fullstackapplication.tip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

//        받아온 Url 값으로 해당 웹페이지가 WebView에 뜨게 만들자

        val wv = findViewById<WebView>(R.id.wv)

        /* WebView에 원하는 웹피이지 띄우기 !
        * 1. 주소준비 -> val wv = ~
        * 2. 설정 변경 -> val ws = wv.settings
        *                 ws.javaScriptEnabled = true
        * 3. 클라이언트 설정 -> wv.webViewClient = WebViewClient()
        * 4. 화면에 로딩 ->  wv.loadUrl(url) */

//        설정 변경 (JavaScript 사용 가능하도록 '허용')
        val ws = wv.settings
        ws.javaScriptEnabled = true

//        WebView 에 Client 설정
        wv.webViewClient = WebViewClient()

        val sharedPreferences = this.getSharedPreferences("sp1", Context.MODE_PRIVATE)
        val url: String? = sharedPreferences.getString("url", "https://smhrd.or.kr/")
        wv.loadUrl(url!!)

    }
}