package com.vivy.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivy.data.model.RestaurantResponse
import com.vivy.data.repository.VivyRepository
import com.vivy.ui.base.BaseViewModel
import com.vivy.utils.Constants
import com.vivy.utils.DataWrapper
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val vivyRepository: VivyRepository) : BaseViewModel() {
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
        vivyRepository.getRestaurants(searchMutableLiveData)
    }

    override fun onCleared() {
        vivyRepository.dispose()
        super.onCleared()
    }
}
