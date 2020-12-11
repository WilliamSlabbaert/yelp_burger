package com.example.yelpproject20.Repo.RestaurantData

import com.example.yelpproject20.model.BusinessData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepo (private val database: RestaurantDB){

    fun getAllRestaurants(): List<RestaurantEntity> = database.RestoDao().getAllRestaurant()

    suspend fun insertRestaurant(body: BusinessData) {
        withContext(Dispatchers.IO) {
            if(!body.equals(null)) {
                deleteAllRestaurants()
                for (item in body.businesses){
                    database.RestoDao().insertRestaurant(RestaurantEntity(item.name,
                        item.imageUrl,
                        item.rating,
                        item.is_closed,
                        item.phone,
                        item.coordinates.latitude,
                        item.coordinates.longitude,
                        item.location.address1,
                        item.location.city,
                        item.location.zip_code))
                }
            }
        }
    }
    suspend  fun deleteAllRestaurants(){
        withContext(Dispatchers.IO){
            database.RestoDao().deleteAllRestaurants()
        }
    }

}