package com.example.yelpproject20.Repo.RestaurantData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yelpproject20.model.Restaurant


@Entity(tableName = "restaurant_table")
data class RestaurantEntity(
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "image")val imageUrl:String,
    @ColumnInfo(name = "rating")val rating:Double,
    @ColumnInfo(name = "open")val is_closed: Boolean,
    @ColumnInfo(name = "phone")val phone:String,
    @ColumnInfo(name = "latitude")val latitude:Double,
    @ColumnInfo(name = "longitude")val longitude:Double,
    @ColumnInfo(name = "adress")val adress:String,
    @ColumnInfo(name = "city")val city:String,
    @ColumnInfo(name = "zip")val zip:String
){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toDomain(): Restaurant {
        return Restaurant(name,imageUrl,rating,is_closed,phone,latitude,longitude,adress,city,zip)
    }
}