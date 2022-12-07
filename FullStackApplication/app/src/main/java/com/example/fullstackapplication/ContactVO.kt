package com.example.fullstackapplication

data class ContactVO(val name: String, val age: Int, val tel: String) {

//    만약, Firebase 의 RealTime Database 용으로 사용되는 VO class 라면,
//    반드시 기본 생성자가 정의되어야 한다

    constructor():this("", 0 ,"")
}