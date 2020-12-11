package com.example.yelpproject20.netwerk

import com.example.yelpproject20.model.BusinessData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("v3/businesses/search?term=burgers&location=New-York")
    fun GetbusinessData(@Header("AUTHORIZATION") value: String) : Call<BusinessData>


    companion object {
         fun GetCall(): ApiService? {

            val BaseUrl = "https://api.yelp.com/"
            //Create Retrofit Get Call
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrl)
                .build()
                .create(ApiService::class.java)

            return retrofit
        }
    }
}