package com.vivy.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vivy.injection.scope.ViewModelScope
import com.vivy.ui.home.HomeViewModel
import com.vivy.ui.login.LoginViewModel
import com.vivy.ui.search.SearchViewModel
import com.vivy.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

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
