package com.haksoy.kotlinmvvm.data

import com.haksoy.kotlinmvvm.data.entiries.User
import com.haksoy.kotlinmvvm.data.remote.OperationCallback
import com.haksoy.kotlinmvvm.data.remote.UserDataSource
import com.haksoy.kotlinmvvm.data.remote.UserRemoteDataSource

class UserRepository() {
    val userDataSource: UserDataSource = UserRemoteDataSource()
    fun fetchUsers(count: Int, callback: OperationCallback<List<User>>) {
        userDataSource.retrieveUsers(count, callback)
    }

}