package com.vivy.injection.builder

import com.vivy.ui.search.SearchFragment
import com.vivy.ui.search.SearchFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(
            modules = [(SearchFragmentModule::class)])
    internal abstract fun bindSearchFragmentModule(): SearchFragment

}
