package com.vivy.ui.login

import androidx.lifecycle.ViewModel
import com.vivy.injection.builder.BaseActivityModule
import com.vivy.injection.scope.ViewModelScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BaseActivityModule::class])
abstract class LoginActivityModule {
    @Binds
    @IntoMap
    @ViewModelScope(LoginViewModel::class)
    abstract fun bindLoginModel(loginViewModel: LoginViewModel): ViewModel
}