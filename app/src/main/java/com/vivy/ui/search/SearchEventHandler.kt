package com.vivy.ui.search

class SearchEventHandler {

    fun onRetrySearch(searchViewModel: SearchViewModel, query: String) {
        searchViewModel.searchDoctors(query)
    }
}