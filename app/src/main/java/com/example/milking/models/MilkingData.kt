package com.example.milking.models

import java.util.*

data class MilkingData(

    val id: Int,
    val cowId:Int,
    val amount:Float,
    val createdOn: String = ""
)