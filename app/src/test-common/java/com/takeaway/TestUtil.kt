package com.takeaway

import com.takeaway.data.model.Restaurant
import com.takeaway.data.model.SortingValues
import com.takeaway.data.model.Status

object TestUtil {
    val RESTAURANT = Restaurant("Tandoori Express", SortingValues(1, 2, 3, 4, 5, 6, 7, 8.0), Status.OPEN, true)
    val RESTAURANTS =
            object : ArrayList<Restaurant>() {
                init {
                    add(RESTAURANT)
                    add(Restaurant("Royal Thai", SortingValues(2, 2, 3, 4, 5, 6, 7, 1.0), Status.CLOSED, false))
                    add(Restaurant("Sushi One", SortingValues(3, 2, 3, 4, 5, 6, 7, 8.0), Status.ORDER_AHEAD, false))
                }
            }
}
