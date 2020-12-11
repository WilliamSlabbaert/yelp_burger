package com.example.yelpproject20.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yelpproject20.Repo.BusinessRepo
import com.example.yelpproject20.Repo.RestaurantData.RestaurantDB
import com.example.yelpproject20.Repo.RestaurantData.RestaurantEntity
import com.example.yelpproject20.Repo.RestaurantData.RestaurantRepo
import com.example.yelpproject20.Repo.RestaurantData.preLoadList
import com.example.yelpproject20.model.BusinessData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.File

class SplashViewModel(private val app:Application): ViewModel() {
    private val repo : BusinessRepo = BusinessRepo()

    fun setData(path:String){
        viewModelScope.launch {
            repo.getRestaurantData(path);
            val tempList:BusinessData = getData(path)
            RestaurantRepo(RestaurantDB.getDatabase(app)).insertRestaurant(tempList)
            Thread{
                preLoadList = RestaurantRepo(RestaurantDB.getDatabase(app)).getAllRestaurants()
            }.start()
        }
    }
    fun getData(path:String): BusinessData {
        val file = File(path).readText()
        return Gson().fromJson(file, BusinessData::class.java)
    }
}