package com.takeaway.data.db


import androidx.room.*

import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant

import androidx.sqlite.db.SupportSQLiteQuery


@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setRestaurants(restaurants: List<Restaurant>)

    @RawQuery
    fun getRestaurants(query: SupportSQLiteQuery): List<Restaurant>

    @Query("SELECT * FROM restaurants WHERE name =:name")
    fun getRestaurantByName(name: String): Restaurant

    @Update
    suspend fun update(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants WHERE name LIKE :query")
    fun searchRestaurantsByName(query: String): List<Restaurant>


    @Query("SELECT restaurantName FROM favourites")
    suspend fun getFavouriteRestaurantNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(favourite: Favourite)

    @Delete
    suspend fun removeFavourite(favourite: Favourite)




}
