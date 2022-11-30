package com.example.directapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etWeb = findViewById<EditText>(R.id.etWeb)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val btnAdd2 = findViewById<Button>(R.id.btnAdd2)
//        var webList = ArrayList<WebVO>()

        btnAdd2.setOnClickListener {

            var web = etWeb.text.toString()
            var url = etUrl.text.toString()

//            webList.add(WebVO(web, url))

            val intent = Intent()

            intent.putExtra("web", web)
            intent.putExtra("url", url)

            setResult(RESULT_OK, intent)

            finish()
        }
    }
}