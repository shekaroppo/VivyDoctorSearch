package com.takeaway.data.repository

import com.takeaway.data.db.RestaurantDatabase
import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant
import com.takeaway.data.services.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import androidx.sqlite.db.SimpleSQLiteQuery
import com.takeaway.data.TakeawayPreferences


@Singleton
class TakeawayRepository @Inject constructor(private val apiService: ApiService,
                                             private val database: RestaurantDatabase,
                                             private val preferences: TakeawayPreferences) : BaseRepository() {
    val BASE_QUERY = "SELECT * FROM restaurants ORDER BY favourite DESC, status ASC, "
    suspend fun getRestaurantsFromServer(): List<Restaurant> {
        val favourites = database.restaurantDao().getFavouriteRestaurantNames()
        val restaurants = apiService.getRestaurants().restaurants
        val restaurantsWithFav = updateFavourites(favourites, restaurants)
        database.restaurantDao().setRestaurants(restaurantsWithFav)
        val query = SimpleSQLiteQuery(BASE_QUERY + preferences.getSortingValue)
        return database.restaurantDao().getRestaurants(query)
    }

    private fun updateFavourites(favourites: List<String>, restaurants: List<Restaurant>): List<Restaurant> {
        for (restaurant in restaurants) {
            if (favourites.contains(restaurant.name)) {
                restaurant.favourite = true
            }
        }
        return restaurants
    }

    fun setFavorite(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            database.restaurantDao().setFavorite(favourite)
            val restaurant = database.restaurantDao().getByName(favourite.restaurantName)
            restaurant.favourite = true
            database.restaurantDao().update(restaurant)
        }
    }

    fun removeFavourite(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            database.restaurantDao().removeFavourite(favourite)
            val restaurant = database.restaurantDao().getByName(favourite.restaurantName)
            restaurant.favourite = false
            database.restaurantDao().update(restaurant)
        }
    }

    fun getSortingValue(): TakeawayPreferences.SortType {
        return preferences.sortType
    }

    fun getRestaurantsSorted(value: TakeawayPreferences.SortType): List<Restaurant> {
        preferences.sortType = value
        val query = SimpleSQLiteQuery(BASE_QUERY+preferences.getSortingValue)
        return database.restaurantDao().getRestaurants(query)
    }
}
