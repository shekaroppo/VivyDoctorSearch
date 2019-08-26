package com.takeaway.data.model

import androidx.annotation.NonNull
import androidx.room.*
import com.takeaway.utils.Constants

data class RestaurantResponse(
        val restaurants: List<Restaurant>
)

@Entity(tableName = Constants.TABLE_RESTAURANTS)
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
        @ColumnInfo(name = "avg_prod_price")
        val averageProductPrice: Int,
        @ColumnInfo(name = "best_match")
        val bestMatch: Int,
        @ColumnInfo(name = "delivery_costs")
        val deliveryCosts: Int,
        val distance: Int,
        @ColumnInfo(name = "min_cost")
        val minCost: Int,
        val newest: Int,
        val popularity: Int,
        @ColumnInfo(name = "rating_avg")
        val ratingAverage: Double
)

@Entity(tableName = Constants.TABLE_RESTAURANTSFTS)
@Fts4(contentEntity = Restaurant::class)
class RestaurantFtsEntity(val name: String)

@Entity(tableName = Constants.TABLE_FAVOURITES)
data class Favourite(@field:PrimaryKey
                     @field:NonNull
                     var restaurantName: String)


