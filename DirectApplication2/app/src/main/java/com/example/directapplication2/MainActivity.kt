package com.example.directapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var webList = ArrayList<WebVO>()
    lateinit var adapter : WebAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lv = findViewById<ListView>(R.id.lv)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        adapter = WebAdapter(
            applicationContext,
            R.layout.web_list,
            webList
        )

        lv.adapter = adapter

        btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)

            launcher.launch(intent)
        }
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.d("data", it.data?.getStringExtra("web").toString())
        Log.d("data", it.data?.getStringExtra("url").toString())

        if (it.resultCode == RESULT_OK) {

            var web = it.data?.getStringExtra("web").toString()
            var url = it.data?.getStringExtra("url").toString()

            webList.add(WebVO(web, url))

            adapter.notifyDataSetChanged()
        }
    }


}