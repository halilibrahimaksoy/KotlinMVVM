package com.haksoy.kotlinmvvm.data.remote

import com.haksoy.kotlinmvvm.data.entiries.User
import retrofit2.Call
import retrofit2.http.*

interface UserListService {
    @GET("contacts/")
    fun getUsers(@Query("page") page: Int, @Query("limit") limit: Int): Call<List<User>>

    @POST("contacts/")
    fun addNewUser(): Call<User>

    @DELETE("contacts/{Id}")
    fun deleteUser(@Path("Id") id: String): Call<User>

    @PUT("contacts/{Id}")
    fun editUser(@Path("Id") id: String, @Body user: User): Call<User>
}