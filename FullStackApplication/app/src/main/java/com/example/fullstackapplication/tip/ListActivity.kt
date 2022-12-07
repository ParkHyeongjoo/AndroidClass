package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val tvTipCategory = findViewById<TextView>(R.id.tvTipCategory)
        val rvTip = findViewById<RecyclerView>(R.id.rvTip)

        val tipList = ArrayList<ListVO>()
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png",
                "아웃백 투움바파스타",
                "https://philosopher-chan.tistory.com/1238",
                "요리레시피"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png",
                "꿀호떡샌드위치",
                "https://philosopher-chan.tistory.com/1242",
                "요리레시피"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png",
                "참치맛나니",
                "https://philosopher-chan.tistory.com/1248",
                "요리레시피"
            )
        )
        tipList.add(
            ListVO(
                "https://www.kyci.or.kr/userSite/cardnews/img/cardnews02_01.jpg",
                "생활관리 꿀팁",
                "https://www.kyci.or.kr/userSite/cardnews/cardnews02.asp",
                "생활"
            )
        )
        tipList.add(
            ListVO(
                "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202211/23/9c0bd39f-3e80-4317-85a5-4a4b470d80bc.jpg",
                "재벌집 막내아들",
                "https://namu.wiki/w/%EC%9E%AC%EB%B2%8C%EC%A7%91%20%EB%A7%89%EB%82%B4%EC%95%84%EB%93%A4(%EB%93%9C%EB%9D%BC%EB%A7%88)",
                "생활"
            )
        )

        val category: String = intent.getStringExtra("category")!!

        tvTipCategory.text = category

        val adapter = ListAdapter(
            this@ListActivity,
            if (category == "전체보기") {
                tipList
            } else {
                tipList.filter { it.category == category } as ArrayList<ListVO>
            }
        )


        rvTip.adapter = adapter
        rvTip.layoutManager = GridLayoutManager(this, 2)

    }
}