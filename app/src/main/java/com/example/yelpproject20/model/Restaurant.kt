package com.example.yelpproject20.model

import androidx.room.ColumnInfo

class Restaurant(
    val name:String,
    val imageUrl:String,
    val rating:Double,
    val is_closed: Boolean,
    val phone:String,
    val latitude:Double,
    val longitude:Double,
    val adress:String,
    val city:String,
    val zip:String
)