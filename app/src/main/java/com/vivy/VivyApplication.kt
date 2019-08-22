package com.vivy

import com.vivy.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class VivyApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<VivyApplication> = DaggerAppComponent.factory().create(this)
}
