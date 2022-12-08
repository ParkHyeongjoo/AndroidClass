package com.example.fullstackapplication.tip

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter

    //    게시물의 uid 값이 들어갈 가변배열 (북마크 확인용)
    var keyData = ArrayList<String>()

    //    bookmark 경로 설정을 위한 선언
    lateinit var bookmarkRef: DatabaseReference

    //    bookmark 된 게시물의 정보가 들어갈 배열
    var bookmarkList = ArrayList<String>()

    var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

//        Realtime Database 에 필요한 객체선언
        val database = Firebase.database
//        database 에 어떤 것을 참조할건지
        val allContent = database.getReference("content")
        bookmarkRef = database.getReference("bookmarklist")

//        Fragment2에서 전체보기를 눌렀을 때 가져올 데이터 담기는 곳
        val tvTipCategory = findViewById<TextView>(R.id.tvTipCategory)
        val rvTip = findViewById<RecyclerView>(R.id.rvTip)

        val tipList = ArrayList<ListVO>()
/*       tipList.add(
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

        //        push() : key 타임스탬프를 찍어줌 (고유한 값을 만들어 줌)

        for (i in 0 until tipList.size){
            allContent.push().setValue(
                tipList[i]
            )
        }*/

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("snapshot", snapshot.toString())

                for (model in snapshot.children) {
                    val item = model.getValue(ListVO::class.java)
//                    model 에는 여러가지 게시물이 담겨있고
//                    1개에 대한 게시물에 접근하기 위해 value 를 ListVO
                    if (item != null) {
                        tipList.add(item)
                    }
                    keyData.add(model.key.toString())
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        allContent.addValueEventListener(postListener)

        getBookmarkData()

        val category: String = intent.getStringExtra("category")!!

        tvTipCategory.text = category

        adapter = ListAdapter(
            this@ListActivity,
            if (category == "전체보기") {
                tipList
            } else {
                tipList.filter { it.category == category } as ArrayList<ListVO>
            },
            keyData,
            bookmarkList
        )

        rvTip.adapter = adapter
        rvTip.layoutManager = GridLayoutManager(this, 2)
    }

    //    bookmarklist 에 저장되어 있는 데이터를 가져오는 함수
    fun getBookmarkData() {
        val postListener2 = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarkList.clear()

                for (model in snapshot.children) {
                    Log.d("bookmark", model.toString())
                    Log.d("bookmark", model.key.toString())
//                    북마크 게시물의 uid 값을 bookmarkList에 저장
//                    bookmarkList.add(model.value.toString())
                    bookmarkList.add(model.key.toString())
                    Log.d("datasize", bookmarkList.size.toString())
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
//        bookmarklist 경로에 있는 데이터를 snapshot 으로 받아옴
        bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)
    }
}