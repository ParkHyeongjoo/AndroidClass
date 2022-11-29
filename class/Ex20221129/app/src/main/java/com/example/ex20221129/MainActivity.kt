package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        1. MainActivity에서 버튼을 누르면 Sub한테 이동(요청)
//            :StartActivityForResult(Intent, 1004)
//        2. Sub에서 버튼을 눌렀을 때 EditText에 담겨있는 값을 들고 Main으로 온다
//        3. Main에서 값을 받아줘야한다 (OnActivityResult() 오버라이딩
//                                      -> intent에서 데이터를 꺼내주는 메소드)

        val btnNext = findViewById<Button>(R.id.btnNext)
        tvResult = findViewById<TextView>(R.id.tvResult)

        btnNext.setOnClickListener {
//            Intent 생성
            val intent = Intent(this@MainActivity, SubActivity::class.java)
//            Intent 실행 (SubActivity 이동 및 요청)
            launcher.launch(intent)
//            실행 하려면 launch() 키워드가 붙어줘야함 전달인자로 내가 생성한 intent 넣어주기

/*            startActivityForResult(intent, 1004)
//            requestCode : 결과값을 보내 줘야하는 Activity를 구분하기 위한 코드*/
        }
    }

/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        1) requsetCode : (1004) 내가 보낸 요청코드를 받아오는 매개변수
//        2) resultCode : (RESULT_OK) 받아온 결과값의 상태
//        3) Intent? data : str(문구)가 붙어있는 Intent를 받아오는 매개변수

//        내가 보냈던 요청이 맞는지 == 요청코드가 1004가 맞나요 ?
        if (requestCode == 1004) {
//            resultCode도 내가 원하는 결과값이 맞는지 (RESULT_OK가 맞는지?)
            if (resultCode == RESULT_OK) {
//                두 가지 다 만족하면 받아온 결과값을 처리 하겠습니다
//                tvResult의 text를 받아온 str로 바꾸자
//                1. str 꺼내기
                val str = data?.getStringExtra("content")
//                2. TextView내용을 str로 바꾸기
                tvResult.text = str
            }
        }
    }*/

//    callback 함수
//    1. 다른 함수의 인자로 사용되는 함수
//    2. 어떤 이벤트에 의해 호출되어지는 함수 ---> 버튼클릭하면 Intent 실행시키면서 호출

//    ActivityResultLauncher를 사용
//    : Avticity에서 data를 받아오기 위해 사용하는 함수
//    : Fragment(부분화면)에서도 data를 주고 받을 때 사용할 수 있음
//    기존에 ActivityForResult는 메모리가 너무 작음 -> 프로세스, 액티비티 소멸될 수 있다
//    런처는 메모리부족으로 소멸되었다가 재생성해도 결과값을 받아온다

    //    Contract자료형, 콜백메서드
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        실제로 ActivityResultConstracts를 타고 들어가면
//        1) createIntent ---> StartActivityForResult 대체
//        2) parseResult ---> onActivityResult 대체 (resultCode랑 Intent만 받아온다)
//        런처를 사용하게 되면 요청을 보낸 Activity를 구분할 필요가 없다
//        requestCode가 필요가 없습니다

//        it에서 resultCode랑 data를 추출
//        Log.d("data", it.toString())
        Log.d("data", it.data.toString())
        Log.d("data", it.resultCode.toString())

//        ResultCode가 RESULT_OK 인지 확인
        if(it.resultCode == RESULT_OK){
            tvResult.text = it.data?.getStringExtra("content")
        }
    }


}