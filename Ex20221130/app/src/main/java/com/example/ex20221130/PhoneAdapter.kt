package com.example.ex20221130

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PhoneAdapter(val context: Context, val layout: Int, val data: ArrayList<PhoneVO>) :
    BaseAdapter() {
    //    프로터피 : 필드
//    멤버 : 메서드
//    Activity의 힘(Context)을 빌려서 Inflate를 할 수 있는 Inflater를 가져오자
    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    getSystemService : 하드웨어(핸드폰)에 담겨있는 센서들이나, Inflater를 추출 할 수 있는 메서드
//    형변환 필요


    override fun getCount(): Int {
//       ListView의 항목의 개수
        return data.size
    }

    override fun getItem(p0: Int): Any {
//        p0: position
//        position 에 위치한 data를 반환
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
//        position 번째 id값을 반환
        return p0.toLong()
    }

    //    ⭐☆★*
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//        data + template 합친 뷰를 return 해주자

//        findViewById, setContentView : Activity만 할 수 있는 일
//        여기는 class 위에 뷰를 찾아오는 메서드 사용이 불가능
//        Activity의 힘을 빌리 수 있다 ---> inflate
//        Inflate : 코드로 있는 xml 파일을 눈에 보이는 뷰로 바꿔주는 작업
//        inflater 는 inflate 를 할 수 있는 도구
        var view = p1
//        p1 : value 라서 아래에서 다른 값을 넣을 수 없음

//        p0 : 항목의 번호 (position)
//        p1 : 이전에 만들어진 View (xml을 눈에 보이는 형태로 바꾼거)
//        p2 : 모든 뷰(항목)를 담고있는 ListView
        if (p1 == null) {
            view = inflater?.inflate(layout, p2, false)

        }

//        코드로 존재하는 layout을 눈에 보이는 View 객체로 바꿔주자
        val tvName = view?.findViewById<TextView>(R.id.tvName)
        val tvTel = view?.findViewById<TextView>(R.id.tvTel)
        val img = view?.findViewById<ImageView>(R.id.img)
    }
}