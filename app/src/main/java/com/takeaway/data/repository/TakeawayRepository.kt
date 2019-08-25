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

@Singleton
class TakeawayRepository @Inject constructor(private val apiService: ApiService,
                                             private val database: RestaurantDatabase) : BaseRepository() {

    suspend fun getRestaurants(): List<Restaurant> {
        val favourites = database.restaurantDao().favouriteRestaurantNames()
        val restaurants = apiService.getRestaurants().restaurants
        val restaurantsWithFav = updateFavourites(favourites, restaurants)
        database.restaurantDao().insertAll(restaurantsWithFav)
        return restaurantsWithFav
    }

    private fun updateFavourites(favourites: List<String>, restaurants: List<Restaurant>): List<Restaurant> {
        for (restaurant in restaurants) {
            if (favourites.contains(restaurant.name)) {
                restaurant.favourite = true
            }
        }
        return restaurants
    }

    fun addToFavorite(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch { database.restaurantDao().addToFavorite(favourite) }
    }

    fun removeFromFavourite(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch { database.restaurantDao().removeFromFavourite(favourite) }
    }
}
