package com.takeaway.ui.restaurantlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.takeaway.data.model.Restaurant
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.ui.base.BaseViewModel
import com.takeaway.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

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
            val restaurants = withContext(Dispatchers.IO + coroutineExceptionHandler) {
                takeawayRepository.getRestaurants()
            }
            withContext(Dispatchers.Main + coroutineExceptionHandler) {
                displayLoader(false)
                restaurantsMutableLiveData.postValue(restaurants)
            }
        }
    }

    private fun onError(exception: Throwable) {
        displayLoader(false)
        setErrorMessage(true, exception.message!!)
    }

}
