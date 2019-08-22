package com.vivy.ui.search

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vivy.data.provider.LocationProvider
import com.vivy.injection.scope.FragmentScope
import com.vivy.injection.scope.ViewModelScope
import com.vivy.ui.home.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [SearchFragmentBinds::class])
class SearchFragmentModule {

    @Provides
    fun provideSearchAdapter(searchEventHandler: SearchEventHandler): SearchAdapter {
        return SearchAdapter(arrayListOf(), searchEventHandler)
    }

    @Provides
    fun provideSearchEventHandler(): SearchEventHandler {
        return SearchEventHandler()
    }

    @Provides
    fun provideLocationProvider(context: HomeActivity): LocationProvider {
        return LocationProvider(context)
    }
}

@Module
abstract class SearchFragmentBinds {
    @Binds
    @IntoMap
    @ViewModelScope(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}