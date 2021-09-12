package com.example.userlistdemo.networkapi

import com.example.userlistdemo.model.UsersRequest
import retrofit2.http.GET
import retrofit2.http.Path


interface DemoAppApi {
    @GET("api/users")
    suspend fun getCategories(): UsersRequest
}