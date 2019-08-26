package com.takeaway.data.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant

import androidx.sqlite.db.SupportSQLiteQuery
import androidx.room.RawQuery


@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setRestaurants(restaurants: List<Restaurant>)

    @Query("SELECT * FROM restaurants ORDER BY favourite DESC, status ASC")
    suspend fun getRestaurants(): List<Restaurant>

    @RawQuery
    fun getRestaurants(query: SupportSQLiteQuery): List<Restaurant>

    @Query("SELECT restaurantName FROM favourites")
    suspend fun getFavouriteRestaurantNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(favourite: Favourite)

    @Delete
    suspend fun removeFavourite(favourite: Favourite)


//    @Update
//    suspend fun update(restaurant: Restaurant)
//
//    @Query("SELECT * FROM restaurants WHERE name LIKE :name")
//    fun searchByName(name: String): Single<List<Restaurant>>
//
//    @Query("SELECT * FROM restaurants WHERE name =:name")
//    fun getByName(name: String): Restaurant

}
