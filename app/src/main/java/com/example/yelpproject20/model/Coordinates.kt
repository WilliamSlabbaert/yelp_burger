package com.example.yelpproject20.model

import com.google.gson.annotations.SerializedName

class Coordinates (
    @SerializedName("latitude")
    val latitude:Double,

    @SerializedName("longitude")
    val longitude:Double
)