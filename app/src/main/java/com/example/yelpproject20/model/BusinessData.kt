package com.example.yelpproject20.model

import com.google.gson.annotations.SerializedName

class BusinessData (
    @SerializedName("businesses")
    val businesses : List<Business>
)