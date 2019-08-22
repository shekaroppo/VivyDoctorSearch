package com.vivy.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vivy.injection.builder.BaseActivityModule
import com.vivy.injection.scope.ActivityScope
import com.vivy.injection.scope.ViewModelScope
import com.vivy.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [BaseActivityModule::class])
abstract class HomeActivityModule {
    @Binds
    @IntoMap
    @ViewModelScope(HomeViewModel::class)
    abstract fun bindHomeModel(homeViewModel: HomeViewModel): ViewModel

    @Module
    abstract class HomeModuleBinds {
        @Binds
        @ActivityScope
        abstract fun bindContext(activity: HomeActivity): Context
    }
}