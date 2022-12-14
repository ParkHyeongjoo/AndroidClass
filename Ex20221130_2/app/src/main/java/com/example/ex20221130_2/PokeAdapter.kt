package com.example.ex20221130_2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
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

    //    ๐๐๐๐๐
//    p0 : position
//    p1 : itemView
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//    0. xmml -> Kotlin : inflater
        val layoutInflater = LayoutInflater.from(context)

//    1. poke_list.xml -> ์ฝ๋๋ก ์ ๊ทผํ  ์ ์๊ฒ
        var view = layoutInflater.inflate(R.layout.poke_list, null)

        var holder = ViewHolder()
        if (p1 == null) {
            Log.d("ํธ์ถ", "1")
//            ํญ๋ชฉ ๋ทฐ(itemView)๊ฐ ์ ๋ง๋ค์ด์ก์ ๋!
//            ๊ฐ component๋ค์ ์ด๊ธฐํ ์์ผ์ฃผ์
            holder.imgPoke = view.findViewById<ImageButton>(R.id.imgPoke)
            holder.tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)
            holder.tvPokeLv = view.findViewById<TextView>(R.id.tvPokeLv)
            holder.tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)

            view.tag = holder

        } else {
            Log.d("ํธ์ถ", "2")
            holder = p1.tag as ViewHolder
            view = p1
        }

        /*  val imgPoke = view.findViewById<ImageButton>(R.id.imgPoke)
          val tvPokeLv = view.findViewById<TextView>(R.id.tvPokeLv)
          val tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
          val tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)*/

        holder.imgPoke?.setImageResource(pokeList[p0].img)
        holder.tvPokeLv?.text = "๋ ๋ฒจ : " + pokeList[p0].level
        holder.tvPokeName?.text = pokeList[p0].name
        holder.tvPokeType?.text = "ํ์ : " + pokeList[p0].type

        return view
    }

//    inner Class ์ฌ์ฉํ๋ ์ด์  ?
//    1. ๋ถ๋ชจ ํด๋์ค์ ๋ณ์๋ฅผ ๋ค ์ฌ์ฉํ  ์ ์๋ค
//    2. ์ธ๋ถ์์ ์ด Class๋ฅผ ์ฌ์ฉํ  ์ด์ ๊ฐ ์์ ๋

//    Design Pattern
//    MVC

//    ViewHolder Pattern
//    getView์ findViewById๋ก ์ ๊ทผํ ์ ๋ณด๋ค์
//    ์ ์ฅํ๊ณ  ์๋ class ViewHolder๋ฅผ ๋ง๋ค์ด์
//    ๋ฉ๋ชจ๋ฆฌ์ ์ฑ๋ฅ์ ํฅ์์ํค์!!

    class ViewHolder() {
        var imgPoke: ImageButton? = null
        var tvPokeLv: TextView? = null
        var tvPokeName: TextView? = null
        var tvPokeType: TextView? = null
    }

}