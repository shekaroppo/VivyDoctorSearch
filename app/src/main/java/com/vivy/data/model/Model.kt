package com.vivy.data.model

data class RestaurantResponse(
        val restaurants: List<Restaurant>
)

data class Restaurant(
        val name: String,
        val sortingValues: SortingValues,
        val status: String
)

data class SortingValues(
        val averageProductPrice: Int,
        val bestMatch: Int,
        val deliveryCosts: Int,
        val distance: Int,
        val minCost: Int,
        val newest: Int,
        val popularity: Int,
        val ratingAverage: Double
)