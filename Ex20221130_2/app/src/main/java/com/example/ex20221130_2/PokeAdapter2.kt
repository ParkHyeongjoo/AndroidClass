package com.example.ex20221130_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter2(val context: Context, val pokeList: ArrayList<PokeVO>) :
    RecyclerView.Adapter<PokeAdapter2.ViewHolder>() {

//    Java에서는 OnClickEvent를 구현한다 ! (Interface 형태로)

    //    inner class 명시를 해야 outer class 의 멤버에 접근할 수 있다
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoke: ImageButton
        val tvPokeLv: TextView
        val tvPokeName: TextView
        val tvPokeType: TextView

        init {
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLv = itemView.findViewById(R.id.tvPokeLv)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)

//            1. ListView의 setOnItemClickListener
            itemView.setOnClickListener {
//                해당 아이템의 position 정보가 필요함 ! -> adapterPosition
                val position: Int = adapterPosition
                pokeList.removeAt(position)
                notifyDataSetChanged()
            }
            imgPoke.setOnClickListener {
                val position: Int = adapterPosition
//                피카츄 --> 피카츄입니다
//                Toast.makeText(context, pokeList[position].name + "입니다", Toast.LENGTH_SHORT).show()
//                "Lv : 1 / 피카츄 / 타입 : 전기"
                val level : String = pokeList[position].level
                val name : String = pokeList[position].name
                val type : String = pokeList[position].type
                Toast.makeText(context, "Lv : $level / $name / 타입 : $type", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //    itemView가 없을 때, ViewHolder 생성!
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.poke_list, null)

        return ViewHolder(view)
    }

    //    만들어진 viewHolder 가 있다면, 꺼내 쓰는곳
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgPoke.setImageResource(pokeList[position].img)
        holder.tvPokeLv.text = "레벨 : " + pokeList[position].level
        holder.tvPokeName.text = pokeList[position].name
        holder.tvPokeType.text = "타입 : " + pokeList[position].type
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }
}