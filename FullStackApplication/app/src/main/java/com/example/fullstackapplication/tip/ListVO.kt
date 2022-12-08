package com.example.fullstackapplication.tip

import android.widget.ImageView
import android.widget.TextView

data class ListVO(val img: String, val tv: String, val url: String, val category: String) {

    constructor() : this("", "", "", "")
}