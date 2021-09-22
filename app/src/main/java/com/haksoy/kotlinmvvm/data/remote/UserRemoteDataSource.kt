package com.haksoy.kotlinmvvm.data.remote

import com.haksoy.kotlinmvvm.data.entiries.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRemoteDataSource :
    UserDataSource {

    private val service = ApiClient.build()

    override fun retrieveUsers(page: Int, count: Int, callback: OperationCallback<List<User>>) {
        var call: Call<List<User>>? = null
        call = service?.getUsers(page, count)
        call?.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.let {
                    if (response.isSuccessful)
                        callback.onSuccess(it)
                    else
                        callback.onError("Error")
                }
            }

        })
    }

    override fun addNewUser(callback: OperationCallback<User>) {
        var call: Call<User>? = null
        call = service?.addNewUser()
        call?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {
                    if (response.isSuccessful)
                        callback.onSuccess(it)
                    else
                        callback.onError("Error")
                }
            }

        })
    }

    override fun deleteUser(id: String, callback: OperationCallback<User>) {
        var call: Call<User>? = null
        call = service?.deleteUser(id)
        call?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {
                    if (response.isSuccessful)
                        callback.onSuccess(it)
                    else
                        callback.onError("Error")
                }
            }

        })
    }

    override fun editUser(user: User, callback: OperationCallback<User>) {
        var call: Call<User>? = null
        call = service?.editUser(user.id, user)
        call?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {
                    if (response.isSuccessful)
                        callback.onSuccess(it)
                    else
                        callback.onError("Error")
                }
            }

        })
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

}

interface UserDataSource {
    fun retrieveUsers(page: Int, count: Int, callback: OperationCallback<List<User>>)
    fun addNewUser(callback: OperationCallback<User>)
    fun deleteUser(id: String, callback: OperationCallback<User>)
    fun editUser(user: User, callback: OperationCallback<User>)
    fun cancel()
}