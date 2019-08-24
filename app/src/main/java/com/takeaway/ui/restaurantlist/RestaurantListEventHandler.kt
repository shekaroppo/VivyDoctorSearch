package com.takeaway.ui.restaurantlist

import android.widget.CheckBox
import com.takeaway.data.db.LocalDataSource
import com.takeaway.data.model.Restaurant

class RestaurantListEventHandler(private val localDataSource: LocalDataSource) {

    fun toggleFavButton(star: CheckBox, restaurant: Restaurant) {
        restaurant.favourite = star.isChecked
        if (star.isChecked) {
            localDataSource.addToFavorite(restaurant.name)
        } else
            localDataSource.removeFromFavourite(restaurant.name)
    }
}