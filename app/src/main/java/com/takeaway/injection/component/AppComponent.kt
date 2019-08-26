package com.takeaway.injection.component

import com.takeaway.TakeawayApplication
import com.takeaway.injection.builder.ActivityBuilder
import com.takeaway.injection.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidInjectionModule::class),
            (AppModule::class),
            (ActivityBuilder::class)])
interface AppComponent : AndroidInjector<TakeawayApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: TakeawayApplication): AppComponent
    }
}

