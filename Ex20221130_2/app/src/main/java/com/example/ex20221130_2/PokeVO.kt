package com.example.ex20221130_2

data class PokeVO(val img : Int , val name: String, val type: String) {

    var level : String

    init{
        level = "1"
    }
}