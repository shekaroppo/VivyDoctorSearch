package com.takeaway.ui.restaurantlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.takeaway.R
import com.takeaway.data.TakeawayPreferences
import com.takeaway.data.model.Restaurant
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.ui.base.BaseViewModel
import com.takeaway.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import kotlin.Throwable as Throwable1

class RestaurantListViewModel @Inject constructor(private val takeawayRepository: TakeawayRepository) : BaseViewModel() {

    val restaurantsMutableLiveData = MutableLiveData<List<Restaurant>>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception -> onError(exception) }

    init {
        getRestaurants()
    }

    fun getRestaurants() {
        setErrorMessage(false, Constants.EMPTY_MESSAGE)
        displayLoader(true)
        viewModelScope.launch(coroutineExceptionHandler) {
            postResults(takeawayRepository.getRestaurantsFromServer())
        }
    }

    fun sortRestaurants(value: TakeawayPreferences.SortType) {
        setErrorMessage(false, Constants.EMPTY_MESSAGE)
        displayLoader(true)
        viewModelScope.launch(coroutineExceptionHandler) {
            postResults(takeawayRepository.sortRestaurants(value))
        }
    }

    fun searchRestaurantsByName(query: String) {
        setErrorMessage(false, Constants.EMPTY_MESSAGE)
        displayLoader(true)
        viewModelScope.launch(coroutineExceptionHandler) {
            takeawayRepository.searchRestaurantsByName(query).let { restaurants ->
                if (restaurants.isEmpty()) onError(Exception("No Result Found")) else postResults(restaurants)
            }
        }
    }

    private fun postResults(restaurants: List<Restaurant>) {
        displayLoader(false)
        restaurantsMutableLiveData.postValue(restaurants)
    }

    private fun onError(exception: Throwable1) {
        displayLoader(false)
        setErrorMessage(true, exception.message!!)
    }

    fun getSortingValue(): TakeawayPreferences.SortType {
        return takeawayRepository.getSortingValue()
    }
}
