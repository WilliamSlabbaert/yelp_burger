package com.example.yelpproject20.Repo

import android.util.Log
import com.example.yelpproject20.model.BusinessData
import com.example.yelpproject20.netwerk.ApiService
import com.google.gson.Gson

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class BusinessRepo(){
    var toastMessage : String? = null;
    val KEY = "YbrqWZG1mDvln0sf5y6Tvd_pzsZlc-O7K2XiT8B2md-9RA6WXgxPA5Ee4qjYIt8aia1G0z8ll-mLy2aKsNIbYNIBIKI8YFols4AteNe0x--sqxX342UlcxMB1htxX3Yx"

    fun getRestaurantData(path:String) {
        ApiService.GetCall()!!.GetbusinessData("Bearer " + KEY).enqueue(object : Callback<BusinessData> {
            override fun onFailure(call: Call<BusinessData>?, t: Throwable?) {
                toastMessage = "ERROR"
            }
            override fun onResponse(call: Call<BusinessData>?, response: Response<BusinessData>?) {
                if (response?.isSuccessful()!!) {
                    try {
                        toastMessage = "WELCOME"
                        setData(response.body(),path)

                    } catch (e: ArithmeticException) {
                        toastMessage = "ERROR"
                    }
                } else {
                    toastMessage = "ERROR"
                }
                Log.d("Answer",toastMessage)
            }
        })
    }
    fun setData(business: BusinessData, path: String){
        val file = File(path)
        file.createNewFile();
        val gson = Gson()
        file.writeText(gson.toJson(business))
    }
}