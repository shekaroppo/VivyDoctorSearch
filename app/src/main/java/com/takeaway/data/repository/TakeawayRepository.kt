package com.takeaway.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.takeaway.data.TakeawayPreferences
import com.takeaway.data.db.RestaurantDao
import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant
import com.takeaway.data.services.ApiService
import com.takeaway.utils.Constants.BASE_QUERY
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TakeawayRepository @Inject constructor(private val apiService: ApiService,
                                             private val restaurantDao: RestaurantDao,
                                             private val preferences: TakeawayPreferences) : BaseRepository() {

    suspend fun getRestaurantsFromServer(): List<Restaurant> {
        val favourites = restaurantDao.getFavouriteRestaurantNames()
        val restaurants = apiService.getRestaurants().restaurants
        val restaurantsWithFav = updateFavourites(favourites, restaurants)
        restaurantDao.setRestaurants(restaurantsWithFav)
        val query = SimpleSQLiteQuery( "$BASE_QUERY, ${preferences.getSortingValue}" )
        return restaurantDao.getRestaurants(query)
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
            restaurantDao.setFavorite(favourite)
            val restaurant = restaurantDao.getRestaurantByName(favourite.restaurantName)
            restaurant.favourite = true
            restaurantDao.update(restaurant)
        }
    }

    fun removeFavourite(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            restaurantDao.removeFavourite(favourite)
            val restaurant = restaurantDao.getRestaurantByName(favourite.restaurantName)
            restaurant.favourite = false
            restaurantDao.update(restaurant)
        }
    }

    fun getSortingValue(): TakeawayPreferences.SortType {
        return preferences.sortType
    }

    suspend fun sortRestaurants(value: TakeawayPreferences.SortType): List<Restaurant> {
        return  withContext(Dispatchers.Unconfined) {
            preferences.sortType = value
            val query = SimpleSQLiteQuery("$BASE_QUERY, ${preferences.getSortingValue}")
            restaurantDao.getRestaurants(query)
        }
    }

    suspend fun searchRestaurantsByName(query: String): List<Restaurant> {
        return  withContext(Dispatchers.IO) {restaurantDao.searchRestaurantsByName(query)}
    }
}
