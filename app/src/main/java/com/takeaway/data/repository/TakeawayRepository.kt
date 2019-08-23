package com.takeaway.data.repository

import androidx.lifecycle.MutableLiveData
import com.takeaway.data.model.*
import com.takeaway.data.services.ApiService
import com.takeaway.utils.CompositeDisposableProvider
import com.takeaway.utils.DataWrapper
import com.takeaway.utils.onBackground
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TakeawayRepository @Inject constructor(private val apiService: ApiService,
                                             private val disposableProvider: CompositeDisposableProvider) : BaseRepository(disposableProvider) {

    fun getRestaurants(searchMutableLiveData: MutableLiveData<DataWrapper<RestaurantResponse>>) {

        disposableProvider.get().add(apiService.getRestaurants()
                .onBackground()
                .subscribe({ response ->
                    searchMutableLiveData.postValue(DataWrapper(data = response))
                }, {
                    searchMutableLiveData.postValue(DataWrapper(isError = true, errorMessage = it.message!!))
                }))
    }
}
