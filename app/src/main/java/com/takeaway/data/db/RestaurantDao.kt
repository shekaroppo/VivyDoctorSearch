package com.takeaway.data.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant

import io.reactivex.Single

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurants(restaurants: List<Restaurant>)

    @Update
    suspend fun update(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants WHERE name LIKE :name")
    fun searchByName(name: String): Single<List<Restaurant>>

    @Query("SELECT * FROM restaurants WHERE name =:name")
    fun getByName(name: String): Restaurant

    @Query("SELECT restaurantName FROM favourites")
    suspend fun getFavouriteRestaurantNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favourite: Favourite)

    @Delete
    suspend fun removeFromFavourite(favourite: Favourite)
}
