package com.example.ex20221202

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_1, container, false)

//        WebView 찾아오기
        val wv = view.findViewById<WebView>(R.id.wv)

//        WebView에 원하는 웹페이지 띄우기
//        1. 주소 지정
//        val url = "https://smhrd.or.kr/"

//        2. 설정 변경 (JavaScript 사용 가능하도록 '허용')
        val ws = wv.settings
        ws.javaScriptEnabled = true

//        3. WebView 에 Client 설정
        wv.webViewClient = WebViewClient()

//        4. WebView 에 1.주소 적용
//        wv.loadUrl(url)


        val sharedPreferences = requireActivity().getSharedPreferences("sp1", Context.MODE_PRIVATE)
        val url: String? = sharedPreferences.getString("url", "https://smhrd.or.kr/")
        wv.loadUrl(url!!)

        return view
    }

}