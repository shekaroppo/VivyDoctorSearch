package com.takeaway.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant

@Database(entities = [Restaurant::class, Favourite::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}
