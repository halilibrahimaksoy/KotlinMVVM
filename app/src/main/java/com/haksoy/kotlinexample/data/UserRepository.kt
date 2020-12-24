package com.haksoy.kotlinexample.data

import UserList
import com.haksoy.kotlinexample.data.remote.ApiClient
import com.haksoy.kotlinexample.data.remote.OperationCallback
import com.haksoy.kotlinexample.data.remote.UserDataSource
import com.haksoy.kotlinexample.data.remote.UserRemoteDataSource

class UserRepository() {
    val userDataSource: UserDataSource = UserRemoteDataSource(ApiClient)
    fun fetchUsers(count: Int, callback: OperationCallback<UserList>) {
        userDataSource.retrieveUsers(count, callback)
    }

    fun cancel() {
        userDataSource.cancel()
    }
}