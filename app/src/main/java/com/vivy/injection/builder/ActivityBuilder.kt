package com.vivy.injection.builder

import androidx.lifecycle.ViewModelProvider
import com.vivy.injection.scope.ActivityScope
import com.vivy.ui.home.HomeActivity
import com.vivy.ui.home.HomeActivityModule
import com.vivy.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity


@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
            modules = [(HomeActivityModule::class), (FragmentBuilder::class)])
    internal abstract fun bindHomeActivity(): HomeActivity
}


@Module
abstract class BaseActivityModule<in T : DaggerAppCompatActivity> {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}