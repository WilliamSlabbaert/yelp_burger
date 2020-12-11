package com.example.yelpproject20.viewmodel


import androidx.lifecycle.*
import com.example.yelpproject20.Repo.BusinessRepo
import com.example.yelpproject20.Repo.RestaurantData.preLoadList
import com.example.yelpproject20.model.Restaurant

class MainViewModel(): ViewModel() {
    var repo : BusinessRepo = BusinessRepo()

    fun setData(path:String){
          repo.getRestaurantData(path);
    }

    fun getData(): List<Restaurant>? {
        return preLoadList?.map { it.toDomain() }
    }

}