package com.takeaway.repository


import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.takeaway.MainCoroutineRule
import com.takeaway.TestUtil
import com.takeaway.data.TakeawayPreferences
import com.takeaway.data.db.RestaurantDao
import com.takeaway.data.model.RestaurantResponse
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.data.services.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
class TakeawayRepositoryTest {

    // system under test
    private lateinit var takeawayRepository: TakeawayRepository

    @Mock
    private lateinit var restaurantDao: RestaurantDao

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var preferences: SharedPreferences

    private lateinit var takeawayPreferences: TakeawayPreferences

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        takeawayPreferences = TakeawayPreferences(preferences)
        takeawayRepository = TakeawayRepository(apiService, restaurantDao, takeawayPreferences)
    }

    @Test
    fun fetchRestaurants_positiveResponse() = runBlockingTest {
        `when`(restaurantDao.getFavouriteRestaurantNames()).thenReturn(listOf())
        `when`(apiService.getRestaurants()).thenReturn(RestaurantResponse(TestUtil.RESTAURANTS))
        `when`(restaurantDao.getRestaurants(com.nhaarman.mockitokotlin2.any())).thenReturn(TestUtil.RESTAURANTS)
        val restaurantsList = takeawayRepository.getRestaurantsFromServer()
        assertNotNull(restaurantsList)
        assertThat(restaurantsList).isEqualTo(TestUtil.RESTAURANTS)
    }
}






























