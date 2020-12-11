package com.example.yelpproject20.Repo.RestaurantData

import androidx.room.*
import com.example.yelpproject20.Repo.RestaurantData.RestaurantEntity

@Dao
interface RestoDao {

    @Query("SELECT * FROM restaurant_table")
    fun getAllRestaurant() : List<RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurantEntity: RestaurantEntity)

    @Query("DELETE FROM restaurant_table")
    fun deleteAllRestaurants();
}