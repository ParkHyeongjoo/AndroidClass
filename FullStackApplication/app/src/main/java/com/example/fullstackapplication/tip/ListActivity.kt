package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val tvTipCategory = findViewById<TextView>(R.id.tvTipCategory)
        val rvTip = findViewById<RecyclerView>(R.id.rvTip)

        val tipList = ArrayList<ListVO>()
        tipList.add(ListVO(R.drawable.main_icon_cook,"요리하기",R.drawable.bookmark_white))
        tipList.add(ListVO(R.drawable.main_icon_cook,"요리하기",R.drawable.bookmark_white))
        tipList.add(ListVO(R.drawable.main_icon_cook,"요리하기",R.drawable.bookmark_white))

        val adapter = ListAdapter(
            this@ListActivity,
            tipList
        )

        rvTip.adapter = adapter
        rvTip.layoutManager = GridLayoutManager(this,2)

    }
}