package com.takeaway.ui.restaurantlist

class RestaurantEventHandler {
    fun onReload(restaurantListViewModel: RestaurantListViewModel) {
        restaurantListViewModel.getRestaurants()
    }
}