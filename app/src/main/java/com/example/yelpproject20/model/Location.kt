package com.example.yelpproject20.model

import com.google.gson.annotations.SerializedName

class Location(
    @SerializedName("address1")
    val address1:String,

    @SerializedName("address2")
    val address2:String,

    @SerializedName("city")
    val city:String,

    @SerializedName("zip_code")
    val zip_code:String,

    @SerializedName("country")
    val country:String

)