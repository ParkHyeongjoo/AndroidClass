package com.example.fullstackapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val context: Context, val contactList: ArrayList<ContactVO>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgContactProfile : ImageView
        val tvContactName : TextView
        val tvContactAge : TextView
        val tvContactNumber : TextView
        val btnContactCall : ImageButton

        init{
            imgContactProfile = itemView.findViewById(R.id.imgContactProfile)
            tvContactName = itemView.findViewById(R.id.tvContactName)
            tvContactAge = itemView.findViewById(R.id.tvContactAge)
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber)
            btnContactCall = itemView.findViewById(R.id.btnContactCall)

            btnContactCall.setOnClickListener {
                val position = adapterPosition
                val tel = contactList[position].tel
//            해당 전화번를 가져와서 ACTION_DIAL이 실행되게 만들자
//            ACTION, DATA(URi -> tel: )
                var call = Uri.parse("tel:${tel}")
                val intent = Intent(Intent.ACTION_DIAL, call)

//            새로운 Task(Stack 통)을 만들어서 실행
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

//            Activity 의 힘을 빌려서 Start 할 예정
//            Activity 의 힘 : context
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.contact_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgContactProfile.setImageResource(R.drawable.ic_baseline_person_24)
        holder.tvContactName.text = contactList[position].name
        holder.tvContactAge.text = contactList[position].age.toString()+"세"
        holder.tvContactNumber.text = contactList[position].tel

    }

    override fun getItemCount(): Int {
        return contactList.size
    }


}