package com.vivy.ui.search

import android.content.Context
import com.vivy.data.provider.LocationProvider
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

//    @Provides
//    fun provideLocationProvider(context: Context): LocationProvider {
//        return LocationProvider(context)
//    }

}