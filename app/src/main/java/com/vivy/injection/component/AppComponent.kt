package com.vivy.injection.component

import com.vivy.VivyApplication
import com.vivy.injection.builder.ActivityBuilder
import com.vivy.injection.module.AppModule
import com.vivy.injection.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [(AppModule::class),
            (ViewModelModule::class),
            (AndroidSupportInjectionModule::class),
            (ActivityBuilder::class)])
interface AppComponent : AndroidInjector<VivyApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<VivyApplication>()
}
