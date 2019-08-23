package com.takeaway.injection.builder

import androidx.lifecycle.ViewModelProvider
import com.takeaway.injection.scope.ActivityScope
import com.takeaway.ui.home.HomeActivity
import com.takeaway.ui.home.HomeActivityModule
import com.takeaway.utils.ViewModelFactory
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