package com.example.ex20221130_2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PokeAdapter(val context: Context, val pokeList: ArrayList<PokeVO>) : BaseAdapter() {


    override fun getCount(): Int {
        return pokeList.size
    }

    override fun getItem(p0: Int): Any {
        return pokeList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    //    ★★★★★
//    p0 : position
//    p1 : itemView
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//    0. xmml -> Kotlin : inflater
        val layoutInflater = LayoutInflater.from(context)

//    1. poke_list.xml -> 코드로 접근할 수 있게
        var view = layoutInflater.inflate(R.layout.poke_list, null)

        var holder = ViewHolder()
        if (p1 == null) {
            Log.d("호출", "1")
//            항목 뷰(itemView)가 안 만들어졌을 때!
//            각 component들을 초기화 시켜주자
            holder.imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
            holder.tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)
            holder.tvPokeLv = view.findViewById<TextView>(R.id.tvPokeLv)
            holder.tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)

            view.tag = holder

        } else {
            Log.d("호출", "2")
            holder = p1.tag as ViewHolder
            view = p1
        }

        /*  val imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
          val tvPokeLv = view.findViewById<TextView>(R.id.tvPokeLv)
          val tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
          val tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)*/

        holder.imgPoke?.setImageResource(pokeList[p0].img)
        holder.tvPokeLv?.text = "레벨 : " + pokeList[p0].level
        holder.tvPokeName?.text = pokeList[p0].name
        holder.tvPokeType?.text = "타입 : " + pokeList[p0].type

        return view
    }

//    inner Class 사용하는 이유 ?
//    1. 부모 클래스의 변수를 다 사용할 수 있다
//    2. 외부에서 이 Class를 사용할 이유가 없을 때

//    Design Pattern
//    MVC

//    ViewHolder Pattern
//    getView의 findViewById로 접근한 정보들을
//    저장하고 있는 class ViewHolder를 만들어서
//    메모리의 성능을 향상시키자!!

    class ViewHolder() {
        var imgPoke: ImageView? = null
        var tvPokeLv: TextView? = null
        var tvPokeName: TextView? = null
        var tvPokeType: TextView? = null
    }

}