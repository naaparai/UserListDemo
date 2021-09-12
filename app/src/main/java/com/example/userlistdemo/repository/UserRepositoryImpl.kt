package com.example.userlistdemo.repository

import android.accounts.NetworkErrorException
import com.example.userlistdemo.model.User
import com.example.userlistdemo.networkapi.DemoAppApi
import com.example.userlistdemo.util.Resource
import com.example.userlistdemo.util.isNetworkActive
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val api: DemoAppApi) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return if (isNetworkActive()) {
            Result.success(api.getCategories().data)
        } else {
            Result.failure(NetworkErrorException())
        }
    }
}