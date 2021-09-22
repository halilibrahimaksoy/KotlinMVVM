package com.haksoy.kotlinmvvm.data

import com.haksoy.kotlinmvvm.data.entiries.User
import com.haksoy.kotlinmvvm.data.remote.OperationCallback
import com.haksoy.kotlinmvvm.data.remote.UserDataSource
import com.haksoy.kotlinmvvm.data.remote.UserRemoteDataSource

class UserRepository() {
    private val userDataSource: UserDataSource = UserRemoteDataSource()
    fun fetchUsers(page: Int, count: Int, callback: OperationCallback<List<User>>) {
        userDataSource.retrieveUsers(page, count, callback)
    }

    fun addNewUser(callback: OperationCallback<User>) {
        userDataSource.addNewUser(callback)
    }

    fun deleteUser(id: String, callback: OperationCallback<User>) {
        userDataSource.deleteUser(id, callback)
    }

    fun editUser(user: User, callback: OperationCallback<User>) {
        userDataSource.editUser(user, callback)
    }
}