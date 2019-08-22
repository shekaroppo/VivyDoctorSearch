package com.vivy.data.repository

import androidx.lifecycle.MutableLiveData
import com.vivy.data.model.*
import com.vivy.data.services.ApiService
import com.vivy.utils.CompositeDisposableProvider
import com.vivy.utils.Constants
import com.vivy.utils.DataWrapper
import com.vivy.utils.onBackground
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VivyRepository @Inject constructor(private val apiService: ApiService,
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
