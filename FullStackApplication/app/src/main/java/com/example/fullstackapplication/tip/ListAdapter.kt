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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListAdapter(
    val context: Context,
    val data: ArrayList<ListVO>,
    val keyData: ArrayList<String>,
    val bookmarkList: ArrayList<String>
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    val database = Firebase.database
    val auth: FirebaseAuth = Firebase.auth

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
        val sharedPreferences = context.getSharedPreferences("sp1", Context.MODE_PRIVATE)

        val editor = sharedPreferences?.edit()

        holder.imgContent.setOnClickListener {

            val url = data[position].url
            editor?.putString("url", url)
            editor?.commit()

            val intent = Intent(context, WebViewActivity::class.java)
            context.startActivity(intent)
        }

//        클릭했을 때 색을 바꾸면 기존에 있던 북마크는 색이 안칠해져 있음
//        adapter 가 실행이 되는 순간 북마크로 있던 데이터들은 바로 색칠 될 수 있게 만듦
        if (bookmarkList.contains(keyData[position])) {
            holder.imgMark.setImageResource(R.drawable.bookmark_color)
        } else {
            holder.imgMark.setImageResource(R.drawable.bookmark_white)
        }

//        북마크 이미지 클릭했을 때 해당 게시물의 uid 값이 bookmarklist 경로로 들어가야함
        holder.imgMark.setOnClickListener {
//            Firebase 에 있는 bookmarklist 경로로 접근
            val bookMarkRef = database.getReference("bookmarklist")

            if (bookmarkList.contains(keyData[position])) {
//                북마크 취소
                bookMarkRef.child(auth.currentUser!!.uid).child(keyData[position]).removeValue()
               // holder.imgMark.setImageResource(R.drawable.bookmark_white)
            } else {
//                북마크 추가
                bookMarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")
//                holder.imgMark.setImageResource(R.drawable.bookmark_color)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}