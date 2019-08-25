package com.takeaway.data

import android.content.SharedPreferences

class TakeawayPreferences(private val sharedPreferences: SharedPreferences) {

    fun setSortType(sortType: SortType) {
        sharedPreferences.edit().putInt(KEY_SORT_TYPE, sortType.ordinal).apply()
    }

    //    public SortType getSortType() {
    //        int sortType = sharedPreferences.getInt(KEY_SORT_TYPE, -1);
    //        switch (sortType) {
    //            case 0:
    //                return MIN_COST;
    //            case 1:
    //                return DELIVERY_COSTS;
    //            case 2:
    //                return AVG_PROD_PRICE;
    //            case 3:
    //                return POPULARITY;
    //            case 4:
    //                return DISTANCE;
    //            case 5:
    //                return RATING_AVG;
    //            case 6:
    //                return NEWEST;
    //            case 7:
    //                return BEST_MATCH;
    //            default:
    //                return POPULARITY;
    //        }
    //    }


    enum class SortType private constructor(val label: String) {
        MIN_COST("Minimum Cost"),
        DELIVERY_COSTS("Delivery Cost"),
        AVG_PROD_PRICE("Average Product Price"),
        POPULARITY("Popularity"),
        DISTANCE("Distance"),
        RATING_AVG("Rating Average"),
        NEWEST("Newest"),
        BEST_MATCH("Best Match")
    }

    companion object {

        private val KEY_SORT_TYPE = "keySortType"
    }
}
