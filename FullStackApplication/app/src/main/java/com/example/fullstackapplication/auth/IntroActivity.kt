package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fullstackapplication.MainActivity
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnIntroLogin = findViewById<Button>(R.id.btnIntroLogin)
        val btnIntroJoin = findViewById<Button>(R.id.btnIntroJoin)
        val btnIntroNo = findViewById<Button>(R.id.btnIntroNo)

        auth = Firebase.auth

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
            auth.signInAnonymously().addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    익명 로그인 성공
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@IntroActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
//                    익명 로그인 실패
//                    서버 문제
                }
            }
        }
    }
}