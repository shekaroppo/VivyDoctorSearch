package com.takeaway.ui.restaurantlist

class RestaurantEventHandler {
    fun onRetrySearch(restaurantListViewModel: RestaurantListViewModel, query: String) {
        restaurantListViewModel.getRestaurants()
    }
}