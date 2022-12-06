package com.example.fullstackapplication.tip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

class ListAdapter(val context: Context, val data: ArrayList<ListVO>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgCategory: ImageView
        val tvCategory: TextView
        val imgMark: ImageView

        init {
            imgCategory = itemView.findViewById(R.id.imgCategory)
            tvCategory = itemView.findViewById(R.id.tvCategory)
            imgMark = itemView.findViewById(R.id.imgMark)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_template, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgCategory.setImageResource(data[position].img)
        holder.tvCategory.text = data[position].tv
        holder.imgMark.setImageResource(data[position].imgMark)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}