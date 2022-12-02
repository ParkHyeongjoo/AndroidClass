package com.example.ex20221201

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(val context: Context, val colorList: ArrayList<ColorVO>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clColor: ConstraintLayout

        init {
            clColor = itemView.findViewById(R.id.clColor)

            itemView.setOnClickListener {
                val position = adapterPosition
                val color = colorList[position].color

                val sharedPreferences = context.getSharedPreferences("sp1", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("bgColor", color)
                editor.commit()
                (context as Activity).finish()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.color_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color : String = colorList[position].color
        holder.clColor.setBackgroundColor(Color.parseColor(color))
    }

    override fun getItemCount(): Int {
        return colorList.size
    }
}