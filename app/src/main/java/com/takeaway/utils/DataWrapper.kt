package com.takeaway.utils

class DataWrapper<T>(
        var data: T? = null,
        var isError: Boolean = false,
        var errorMessage: String = Constants.EMPTY_MESSAGE)