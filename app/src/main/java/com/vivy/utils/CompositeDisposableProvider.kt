package com.vivy.utils

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CompositeDisposableProvider @Inject constructor() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun get(): CompositeDisposable {
        if (compositeDisposable.isDisposed) compositeDisposable = CompositeDisposable()
        return compositeDisposable
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}