package com.takeaway.ui.restaurantlist

import androidx.lifecycle.ViewModel
import com.takeaway.data.db.LocalDataSource
import com.takeaway.injection.scope.ViewModelScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [RestaurantListFragmentBinds::class])
class RestaurantListFragmentModule {

    @Provides
    fun provideRestaurantListAdapter(restaurantListEventHandler: RestaurantListEventHandler): RestaurantListAdapter {
        return RestaurantListAdapter(arrayListOf(), restaurantListEventHandler)
    }

    @Provides
    fun provideRestaurantEventHandler(): RestaurantEventHandler {
        return RestaurantEventHandler()
    }

    @Provides
    fun provideRestaurantListEventHandler(localDataSource: LocalDataSource): RestaurantListEventHandler {
        return RestaurantListEventHandler(localDataSource)
    }
}

@Module
abstract class RestaurantListFragmentBinds {
    @Binds
    @IntoMap
    @ViewModelScope(RestaurantListViewModel::class)
    abstract fun bindRestaurantListViewModel(restaurantListViewModel: RestaurantListViewModel): ViewModel
}