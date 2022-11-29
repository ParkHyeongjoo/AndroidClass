package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etId = findViewById<EditText>(R.id.etId)
        val etPw = findViewById<EditText>(R.id.etPw)
        val btnLogIn = findViewById<Button>(R.id.btnLogIn)

        btnLogIn.setOnClickListener {
            var loginId = etId.text.toString()
            var loginPw = etPw.text.toString()

            val intent = Intent()

            if (loginId == "smhrd" && loginPw == "1234") {

                intent.putExtra("id",loginId)
                setResult(RESULT_OK, intent)

            } else {

                setResult(RESULT_CANCELED, intent)
            }

            finish()
        }
    }
}