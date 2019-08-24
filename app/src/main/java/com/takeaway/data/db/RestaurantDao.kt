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

    @get:Query("SELECT * FROM restaurants")
    val all: List<Restaurant>

    @get:Query("SELECT * FROM favourites")
    val favourites: List<Favourite>

    @Query("SELECT * FROM restaurants WHERE name LIKE :name")
    fun searchByName(name: String): Single<List<Restaurant>>

    @Query("SELECT * FROM restaurants WHERE name =:name")
    fun getByName(name: String): Restaurant

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorite(favourite: Favourite)

    @Delete
    fun removeFromFavourite(favourite: Favourite)

    @Update
    fun update(restaurant: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(restaurants: List<Restaurant>)
}
