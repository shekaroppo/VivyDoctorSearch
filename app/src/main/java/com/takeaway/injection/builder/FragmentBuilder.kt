package com.takeaway.injection.builder

import com.takeaway.ui.restaurantlist.RestaurantListFragment
import com.takeaway.ui.restaurantlist.RestaurantListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(
            modules = [(RestaurantListFragmentModule::class)])
    internal abstract fun bindSearchFragmentModule(): RestaurantListFragment

}
