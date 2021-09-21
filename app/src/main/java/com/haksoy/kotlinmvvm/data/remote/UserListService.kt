package com.haksoy.kotlinmvvm.data.remote

import com.haksoy.kotlinmvvm.data.entiries.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserListService {
    @GET("contacts/")
    fun getUsers(@Query("page") page: Int, @Query("limit") limit: Int): Call<List<User>>
}