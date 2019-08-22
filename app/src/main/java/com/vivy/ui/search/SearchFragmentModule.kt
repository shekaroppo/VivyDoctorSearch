package com.vivy.ui.search

import androidx.lifecycle.ViewModel
import com.vivy.injection.scope.ViewModelScope
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
}

@Module
abstract class SearchFragmentBinds {
    @Binds
    @IntoMap
    @ViewModelScope(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}