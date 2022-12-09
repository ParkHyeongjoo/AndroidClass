package com.example.fullstackapplication.board

data class BoardVO(val title: String, val content: String, val uid: String, val time: String) {

    constructor() : this("", "", "", "")

}