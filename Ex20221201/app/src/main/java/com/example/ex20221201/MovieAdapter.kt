package com.example.ex20221201

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val context: Context, val movieList: ArrayList<MovieVO>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRank: TextView
        val tvOldAndNew: TextView
        val tvAudiAcc: TextView
        val tvOpenDt: TextView
        val tvTitle: TextView

        init{
            tvRank = itemView.findViewById(R.id.tvRank)
            tvOldAndNew = itemView.findViewById(R.id.tvOldAndNew)
            tvAudiAcc = itemView.findViewById(R.id.tvAudiAcc)
            tvOpenDt = itemView.findViewById(R.id.tvOpenDt)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.movie_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRank.text = (movieList[position].rank)
        holder.tvOldAndNew.text = (movieList[position].rankOldAndNew)
        holder.tvAudiAcc.text = (movieList[position].audiAcc)
        holder.tvOpenDt.text = (movieList[position].openDt)
        holder.tvTitle.text = (movieList[position].movieNm)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}