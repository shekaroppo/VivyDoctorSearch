package com.vivy.data.repository

import com.vivy.utils.CompositeDisposableProvider

open class BaseRepository constructor(private val compositeDisposableProvider: CompositeDisposableProvider) {

    fun dispose() {
        compositeDisposableProvider.dispose()
    }
}