package com.takeaway.data.repository

import com.takeaway.utils.CompositeDisposableProvider

open class BaseRepository constructor(private val compositeDisposableProvider: CompositeDisposableProvider) {

    fun dispose() {
        compositeDisposableProvider.dispose()
    }
}