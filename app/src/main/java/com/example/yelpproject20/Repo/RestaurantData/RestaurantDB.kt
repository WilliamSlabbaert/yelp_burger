package com.example.yelpproject20.Repo.RestaurantData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RestaurantEntity::class), version = 1,exportSchema = false)
abstract class RestaurantDB(): RoomDatabase(){
    abstract fun RestoDao(): RestoDao

    companion object{
        @Volatile private var INSTANCE: RestaurantDB? = null

        fun getDatabase(context: Context): RestaurantDB =
            INSTANCE ?: synchronized(this){ INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}}

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, RestaurantDB::class.java,"Restaurant_DB")
            .fallbackToDestructiveMigration()
                .build()
    }
}
