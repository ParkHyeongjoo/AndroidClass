package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fullstackapplication.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnIntroLogin = findViewById<Button>(R.id.btnIntroLogin)
        val btnIntroJoin = findViewById<Button>(R.id.btnIntroJoin)
        val btnIntroNo = findViewById<Button>(R.id.btnIntroNo)

        btnIntroLogin.setOnClickListener {
            val intent = Intent(
                this@IntroActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }

        btnIntroJoin.setOnClickListener {
            val intent = Intent(
                this@IntroActivity,
                JoinActivity::class.java
            )
            startActivity(intent)
        }

        btnIntroNo.setOnClickListener {

        }
    }
}