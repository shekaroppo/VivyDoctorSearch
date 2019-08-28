package com.takeaway.ui.base

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val loader: ObservableField<Boolean> = ObservableField()
    val errorMessage: ObservableField<String> = ObservableField()
    val isError: ObservableField<Boolean> = ObservableField()

    fun displayLoader(show: Boolean) {
        loader.set(show)
    }

    fun setErrorMessage(show: Boolean, msg: String) {
        isError.set(show)
        errorMessage.set(msg)
    }

}