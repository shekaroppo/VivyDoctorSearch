package com.takeaway.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.takeaway.injection.builder.BaseActivityModule
import com.takeaway.injection.scope.ActivityScope
import com.takeaway.injection.scope.ViewModelScope
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