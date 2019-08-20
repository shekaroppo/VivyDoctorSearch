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

    fun doLogin(email: String, password: String, loginMutableLiveData: MutableLiveData<DataWrapper<LoginResponse>>) {
        val headerMap = HashMap<String, String>()
        headerMap["Content-Type"] = Constants.CONTENT_TYPE
        headerMap["Accept"] = Constants.ACCEPT
        headerMap["Authorization"] = Constants.AUTHORIZATION
        disposableProvider.get().add(apiService.doLogin(email, password, headerMap)
                .onBackground()
                .subscribe({ response ->
                    loginMutableLiveData.postValue(DataWrapper(data = response))
                }, {
                    loginMutableLiveData.postValue(DataWrapper(isError = true, errorMessage = it.message!!))
                }))
    }

    fun searchDoctors(query: String, searchMutableLiveData: MutableLiveData<DataWrapper<SearchResultResponse>>) {

        disposableProvider.get().add(apiService.searchDoctors(hashMapOf("search" to query, "lat" to Constants.LATITUDE, "lng" to Constants.LONGITUDE),
                hashMapOf("Authorization" to "Bearer ${Constants.ACCESS_TOKEN}"))
                .onBackground()
                .subscribe({ response ->
                    searchMutableLiveData.postValue(DataWrapper(data = response))
                }, {
                    searchMutableLiveData.postValue(DataWrapper(isError = true, errorMessage = it.message!!))
                }))
    }

    fun fetchMoreDoctors(query: String, lastKey: String,searchMutableLiveData: MutableLiveData<DataWrapper<SearchResultResponse>>) {
        disposableProvider.get().add(apiService.searchDoctors(hashMapOf("search" to query, "lastKey" to lastKey, "lat" to Constants.LATITUDE, "lng" to Constants.LONGITUDE), hashMapOf("Authorization" to "Bearer ${Constants.ACCESS_TOKEN}"))
                .onBackground()
                .subscribe({ response ->
                    searchMutableLiveData.postValue(DataWrapper(data = response))
                }, {
                    searchMutableLiveData.postValue(DataWrapper(isError = true, errorMessage = it.message!!))
                }))
    }
}
