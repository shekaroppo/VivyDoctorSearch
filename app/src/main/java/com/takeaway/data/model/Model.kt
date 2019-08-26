package com.takeaway.data.model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.takeaway.utils.Status
import com.takeaway.utils.StatusConverter

data class RestaurantResponse(
        val restaurants: List<Restaurant>
)

@Entity(tableName = "restaurants")
data class Restaurant(
        @PrimaryKey
        @NonNull
        val name: String,
        @Embedded
        val sortingValues: SortingValues,
        var status: Status,
        var favourite: Boolean = false
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

@Entity(tableName = "favourites")
data class Favourite(@field:PrimaryKey
                     @field:NonNull
                     var restaurantName: String)


