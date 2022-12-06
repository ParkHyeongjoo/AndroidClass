package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.ListActivity

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_2, container, false)

        val imgAll = view.findViewById<ImageView>(R.id.imgAll)
        val imgCook = view.findViewById<ImageView>(R.id.imgCook)
        val imgLife = view.findViewById<ImageView>(R.id.imgLife)

        imgAll.setOnClickListener {
            val intent = Intent(view.context, ListActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}