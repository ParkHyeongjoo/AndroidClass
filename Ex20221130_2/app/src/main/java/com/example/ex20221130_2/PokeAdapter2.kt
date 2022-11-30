package com.example.ex20221130_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter2(val context: Context, val pokeList: ArrayList<PokeVO>) :
    RecyclerView.Adapter<PokeAdapter2.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoke: ImageView
        val tvPokeLv: TextView
        val tvPokeName: TextView
        val tvPokeType: TextView

        init {
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLv = itemView.findViewById(R.id.tvPokeLv)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)
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