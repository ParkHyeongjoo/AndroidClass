package com.example.fullstackapplication.tip

import android.content.Context
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

class BookmarkAdapter(
    val context: Context,
    val data: ArrayList<ListVO>,
    val keyData: ArrayList<String>,
    val bookmarkList: ArrayList<String>
) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

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
//        북마크 데이터에 포함되어 있는지를 판단해서 view + data 랑 합쳐주는 작업을 진행
        holder.tvCategory.text = data[position].tv
//            Glide : Web 에 있는 이미지를 가져와서 세팅
//            .img ---> drawable 에 저장해둔것이 아니라 이미지 주소 값 이라 Glide 를 사용
        Glide.with(context).load(data[position].img).into(holder.imgContent)
        holder.imgMark.setImageResource(R.drawable.bookmark_color)

        holder.imgMark.setOnClickListener {
            val bookMarkRef = database.getReference("bookmarklist")
            bookMarkRef.child(auth.currentUser!!.uid).child(keyData[position]).removeValue()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}