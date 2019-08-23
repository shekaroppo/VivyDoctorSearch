package com.takeaway

import com.takeaway.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TakeawayApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<TakeawayApplication> = DaggerAppComponent.factory().create(this)
}
