package com.example.userlistdemo.networkapi

import com.example.userlistdemo.model.UsersRequest
import retrofit2.http.GET


interface DemoAppApi {
    @GET("users")
    suspend fun getCategories(): UsersRequest
}