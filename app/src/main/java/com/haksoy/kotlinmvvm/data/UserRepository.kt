package com.haksoy.kotlinmvvm.data

import UserList
import com.haksoy.kotlinmvvm.data.remote.ApiClient
import com.haksoy.kotlinmvvm.data.remote.OperationCallback
import com.haksoy.kotlinmvvm.data.remote.UserDataSource
import com.haksoy.kotlinmvvm.data.remote.UserRemoteDataSource

class UserRepository() {
    val userDataSource: UserDataSource = UserRemoteDataSource(ApiClient)
    fun fetchUsers(count: Int, callback: OperationCallback<UserList>) {
        userDataSource.retrieveUsers(count, callback)
    }

    fun cancel() {
        userDataSource.cancel()
    }
}