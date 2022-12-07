package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R

class ListAdapter(val context: Context, val data: ArrayList<ListVO>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgContent: ImageView
        val tvCategory: TextView
        val imgMark: ImageView

        init {
            imgContent = itemView.findViewById(R.id.imgContent)
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
//        holder.imgCategory.setImageResource(data[position].img)
        holder.tvCategory.text = data[position].tv
        Glide.with(context).load(data[position].img).into(holder.imgContent)

//        imgView를 클릭했을 때, url 값을 가지고 WebViewActivity 로 이동 !!
        val sharedPreferences = context.getSharedPreferences("sp1",Context.MODE_PRIVATE)

        val editor = sharedPreferences?.edit()

        holder.imgContent.setOnClickListener {

            val url = data[position].url

            editor?.putString("url", url)
            editor?.commit()

            val intent = Intent(context,WebViewActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}