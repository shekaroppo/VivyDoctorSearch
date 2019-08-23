package com.takeaway.ui.restaurantlist

class RestaurantListEventHandler {

    fun onRetrySearch(restaurantListViewModel: RestaurantListViewModel, query: String) {
        restaurantListViewModel.getRestaurants()
    }
}