package com.takeaway.restaurantlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.takeaway.MainCoroutineRule
import com.takeaway.TestUtil
import com.takeaway.data.model.Restaurant
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.ui.restaurantlist.RestaurantListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RestaurantListViewModelTest {

    // system under test
    lateinit var restaurantListViewModel: RestaurantListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var takeawayRepository: TakeawayRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        restaurantListViewModel = RestaurantListViewModel(this.takeawayRepository)
    }

    @Test
    fun fetchRestaurants_positiveResponse() = runBlocking {
        `when`(takeawayRepository.getRestaurantsFromServer()).thenReturn(TestUtil.RESTAURANTS)
        restaurantListViewModel.getRestaurants()
        assertNotNull(restaurantListViewModel.restaurantsMutableLiveData.value)
        assertEquals(restaurantListViewModel.restaurantsMutableLiveData.value, TestUtil.RESTAURANTS)
    }
}