package com.takeaway.data.db


import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant


@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setRestaurants(restaurants: List<Restaurant>)

    @RawQuery
    suspend fun getRestaurants(query: SupportSQLiteQuery): List<Restaurant>

    @Query("SELECT * FROM restaurants WHERE name =:name")
    suspend fun getRestaurantByName(name: String): Restaurant

    @Update
    suspend fun update(restaurant: Restaurant)

    @Query("SELECT restaurants.* FROM restaurants JOIN restaurantsFts ON (restaurants.name = restaurantsFts.name) WHERE restaurantsFts MATCH :query")
    fun searchRestaurantsByName(query: String): List<Restaurant>

    @Query("SELECT restaurantName FROM favourites")
    suspend fun getFavouriteRestaurantNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(favourite: Favourite)

    @Delete
    suspend fun removeFavourite(favourite: Favourite)

}
