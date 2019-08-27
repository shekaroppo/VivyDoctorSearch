package com.takeaway

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.takeaway.data.db.RestaurantDao
import com.takeaway.data.db.RestaurantDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class RestaurantDatabaseTest {

    lateinit var restaurantDatabase: RestaurantDatabase

    val restaurantDao: RestaurantDao
        get() = restaurantDatabase.restaurantDao()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        restaurantDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                RestaurantDatabase::class.java
        ).build()
    }

    @After
    fun finish() {
        restaurantDatabase.close()
    }
}

