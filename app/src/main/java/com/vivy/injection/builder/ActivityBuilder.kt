package com.vivy.injection.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vivy.injection.module.ViewModelModule
import com.vivy.injection.scope.ActivityScope
import com.vivy.injection.scope.ViewModelScope
import com.vivy.ui.home.HomeActivity
import com.vivy.ui.home.HomeActivityModule
import com.vivy.ui.home.HomeViewModel
import com.vivy.ui.login.LoginActivity
import com.vivy.ui.login.LoginActivityModule
import com.vivy.ui.login.LoginViewModel
import com.vivy.ui.search.SearchViewModel
import com.vivy.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
            modules = [(HomeActivityModule::class), (FragmentBuilder::class)])
    internal abstract fun bindHomeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(
            modules = [(LoginActivityModule::class)])
    internal abstract fun bindLoginActivity(): LoginActivity
}


@Module
abstract class BaseActivityModule<in T : DaggerAppCompatActivity>{
    @Binds
    @IntoMap
    @ViewModelScope(HomeViewModel::class)
    abstract fun bindHomeModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(LoginViewModel::class)
    abstract fun bindLoginModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}