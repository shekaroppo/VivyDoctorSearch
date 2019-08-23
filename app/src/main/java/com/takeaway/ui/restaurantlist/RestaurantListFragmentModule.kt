package com.takeaway.ui.restaurantlist

import androidx.lifecycle.ViewModel
import com.takeaway.injection.scope.ViewModelScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [RestaurantListFragmentBinds::class])
class RestaurantListFragmentModule {

    @Provides
    fun provideSearchAdapter(restaurantListEventHandler: RestaurantListEventHandler): RestaurantListAdapter {
        return RestaurantListAdapter(arrayListOf(), restaurantListEventHandler)
    }

    @Provides
    fun provideSearchEventHandler(): RestaurantListEventHandler {
        return RestaurantListEventHandler()
    }
}

@Module
abstract class RestaurantListFragmentBinds {
    @Binds
    @IntoMap
    @ViewModelScope(RestaurantListViewModel::class)
    abstract fun bindSearchViewModel(restaurantListViewModel: RestaurantListViewModel): ViewModel
}