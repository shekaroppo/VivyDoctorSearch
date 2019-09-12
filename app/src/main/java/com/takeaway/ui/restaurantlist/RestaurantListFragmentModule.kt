package com.takeaway.ui.restaurantlist

import androidx.lifecycle.ViewModel
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.injection.scope.ViewModelScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [RestaurantListFragmentBinds::class])
class RestaurantListFragmentModule {

    @Provides
    fun provideRestaurantListAdapter(takeawayRepository: TakeawayRepository): RestaurantListAdapter {
        return RestaurantListAdapter(takeawayRepository)
    }
}

@Module
abstract class RestaurantListFragmentBinds {
    @Binds
    @IntoMap
    @ViewModelScope(RestaurantListViewModel::class)
    abstract fun bindRestaurantListViewModel(restaurantListViewModel: RestaurantListViewModel): ViewModel
}