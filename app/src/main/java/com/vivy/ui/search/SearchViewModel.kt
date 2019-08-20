package com.vivy.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivy.data.model.SearchResultResponse
import com.vivy.data.repository.VivyRepository
import com.vivy.ui.base.BaseViewModel
import com.vivy.utils.Constants
import com.vivy.utils.DataWrapper
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val vivyRepository: VivyRepository) : BaseViewModel() {
    var searchMutableLiveData = MutableLiveData<DataWrapper<SearchResultResponse>>()

    var bottomProgressBar = MutableLiveData<Boolean>().apply { postValue(false) }

    val showBottomProgressBar: LiveData<Boolean>
        get() = bottomProgressBar

    fun searchDoctors(query: String) {
        setErrorMessage(false, Constants.EMPTY_MESSAGE)
        displayLoader(true)
        vivyRepository.searchDoctors(query, searchMutableLiveData)
    }

    fun loadMore(lastKey: String, query: String) {
        bottomProgressBar.value = true
        vivyRepository.fetchMoreDoctors(query, lastKey, searchMutableLiveData)
    }

    override fun onCleared() {
        vivyRepository.dispose()
        super.onCleared()
    }
}
