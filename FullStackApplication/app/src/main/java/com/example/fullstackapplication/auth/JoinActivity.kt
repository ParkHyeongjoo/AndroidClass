package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinPwCk = findViewById<EditText>(R.id.etJoinPwCk)
        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)

        // Initialize Firebase Auth
        auth = Firebase.auth
        // Firebase.auth : 로그인, 회원가입, 인증 시스템에 대한 모든 기능이 담겨있다

        btnJoinJoin.setOnClickListener {
            var isJoin = true // 조건 만족 확인 변수
            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val pwCk = etJoinPwCk.text.toString()

/*            if (pw == pwCk){
                Toast.makeText(this, "Email : $email , Pw : $pw", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "입력하신 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }*/
            if(email.isEmpty()){
                isJoin = false
                Toast.makeText(this,"이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if(pw.isEmpty()){
                isJoin = false
                Toast.makeText(this,"비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if(pwCk.isEmpty()){
                isJoin = false
                Toast.makeText(this,"비밀번호 재입력을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if(pw.length < 8){
                isJoin = false
                Toast.makeText(this, "비밀번호를 8자리 이상으로 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if(pw != pwCk){
                isJoin = false
                Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show()
            }

            if (isJoin) {
//            create가 보내고 있는 전달인자 2개(email, pw)는
//            실제로 회원가입 정보 전달 (Firebase로 전달)
                auth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this) { task ->
//                    task ---> 보낸 후 결과 (성공했는지 실패했는지)
                        if (task.isSuccessful) {
//                      성공했을 때 실행시킬 코드
                            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@JoinActivity, IntroActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
//                      실패했을 때 실행시킬 코드
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}