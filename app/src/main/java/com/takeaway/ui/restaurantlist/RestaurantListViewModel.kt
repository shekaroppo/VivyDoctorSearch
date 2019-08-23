package com.takeaway.ui.restaurantlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.takeaway.data.model.RestaurantResponse
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.ui.base.BaseViewModel
import com.takeaway.utils.Constants
import com.takeaway.utils.DataWrapper
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(private val takeawayRepository: TakeawayRepository) : BaseViewModel() {
    var searchMutableLiveData = MutableLiveData<DataWrapper<RestaurantResponse>>()

    var bottomProgressBar = MutableLiveData<Boolean>().apply { postValue(false) }

    val showBottomProgressBar: LiveData<Boolean>
        get() = bottomProgressBar

    init {
        getRestaurants()
    }

    fun getRestaurants() {
        setErrorMessage(false, Constants.EMPTY_MESSAGE)
        displayLoader(true)
        takeawayRepository.getRestaurants(searchMutableLiveData)
    }

    override fun onCleared() {
        takeawayRepository.dispose()
        super.onCleared()
    }
}
