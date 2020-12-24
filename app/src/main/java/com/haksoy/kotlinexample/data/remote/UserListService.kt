package com.haksoy.kotlinexample.data.remote

import UserList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserListService {
    @GET("api/")
    fun getUsers(@Query("results") results: Int, @Query("nat") nat: String = "tr"): Call<UserList>
}