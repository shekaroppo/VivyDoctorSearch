package com.vivy.ui.search

import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {
    @Provides
    fun provideSearchAdapter(searchEventHandler: SearchEventHandler): SearchAdapter {
        return SearchAdapter(arrayListOf(), searchEventHandler)
    }

    @Provides
    fun provideSearchEventHandler(): SearchEventHandler {
        return SearchEventHandler()
    }

}