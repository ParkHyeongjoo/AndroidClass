package com.example.fullstackapplication

data class ChatVO(val name: String, val msg: String) {

    //    반드시 ! FireBase RealTime DataBase 사용하기 위해서
    //    기본 생성자
    constructor() : this("", "")
}