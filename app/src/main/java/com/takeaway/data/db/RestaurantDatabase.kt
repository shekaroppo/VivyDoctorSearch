package com.takeaway.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant
import com.takeaway.data.model.StatusConverter

@Database(entities = [Restaurant::class, Favourite::class], version = 1, exportSchema = false)
@TypeConverters(StatusConverter::class)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}
