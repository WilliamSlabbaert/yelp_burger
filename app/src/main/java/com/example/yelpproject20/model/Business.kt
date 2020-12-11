package com.example.yelpproject20.model

import com.google.gson.annotations.SerializedName

class Business(
    @SerializedName("id")
    val id:String,

    @SerializedName("name")
    val name:String,

    @SerializedName("image_url")
    val imageUrl:String,

    @SerializedName("rating")
    val rating:Double,

    @SerializedName("is_closed")
    val is_closed: Boolean,

    @SerializedName("coordinates")
    val coordinates: Coordinates,

    @SerializedName("location")
    val location: Location,

    @SerializedName("phone")
    val phone:String

)