package com.haksoy.kotlinmvvm.data.remote

interface OperationCallback<T> {
    fun onSuccess(data: Any?)
    fun onError(error: String?)
}