package com.takeaway

import androidx.sqlite.db.SimpleSQLiteQuery
import com.takeaway.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantDaoTest : RestaurantDatabaseTest() {

    @Test
    fun insertReadUpdate() = runBlockingTest {

        // insert
        restaurantDao.setRestaurants(TestUtil.RESTAURANTS)

        // read
        val restaurants=restaurantDao.getRestaurants(SimpleSQLiteQuery(Constants.BASE_QUERY))
        assertNotNull(restaurants)
        val restaurant=TestUtil.RESTAURANT
        assertEquals(restaurant.name, restaurants[0].name)
        assertEquals(restaurant.status, restaurants[0].status)
        assertEquals(restaurant.favourite, restaurants[0].favourite)

        // update
        restaurant.favourite=false
        restaurantDao.update(restaurant)

        // read
        val updatedRestaurants=restaurantDao.getRestaurants(SimpleSQLiteQuery(Constants.BASE_QUERY))
        assertNotNull(updatedRestaurants)
        assertEquals(false, updatedRestaurants[0].favourite)
    }
}
















