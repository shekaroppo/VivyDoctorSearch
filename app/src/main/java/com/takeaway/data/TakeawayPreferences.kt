package com.takeaway.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.takeaway.data.TakeawayPreferences.SortType.*
import com.takeaway.utils.Constants
import javax.inject.Inject

class TakeawayPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var sortType: SortType
        get() {
            return when (sharedPreferences.getInt(Constants.KEY_SORT_TYPE, -1)) {
                0 -> MIN_COST
                1 -> DELIVERY_COSTS
                2 -> AVG_PROD_PRICE
                3 -> POPULARITY
                4 -> DISTANCE
                5 -> RATING_AVG
                6 -> NEWEST
                7 -> BEST_MATCH
                else -> POPULARITY
            }
        }
        @SuppressLint("ApplySharedPref")
        set(sortType) {
            sharedPreferences.edit().putInt(Constants.KEY_SORT_TYPE, sortType.ordinal).commit()
        }


    val getSortingValue: String
        get() {
            return when (sortType) {
                MIN_COST, DELIVERY_COSTS, AVG_PROD_PRICE, DISTANCE -> sortType.name.toLowerCase() + " ASC"
                POPULARITY, BEST_MATCH, RATING_AVG, NEWEST -> sortType.name.toLowerCase() + " DESC"
            }
        }

    enum class SortType {
        MIN_COST,
        DELIVERY_COSTS,
        AVG_PROD_PRICE,
        POPULARITY,
        DISTANCE,
        RATING_AVG,
        NEWEST,
        BEST_MATCH
    }
}
