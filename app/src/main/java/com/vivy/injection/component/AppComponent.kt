package com.vivy.injection.component

import com.vivy.VivyApplication
import com.vivy.injection.builder.ActivityBuilder
import com.vivy.injection.module.AppModule
import com.vivy.injection.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidInjectionModule::class),
            (AppModule::class),
            (ActivityBuilder::class)])
interface AppComponent : AndroidInjector<VivyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: VivyApplication): AppComponent
    }
}
