package com.example.userlistdemo.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.userlistdemo.model.User
import com.example.userlistdemo.networkapi.DemoAppApi
import com.example.userlistdemo.util.Resource
import com.example.userlistdemo.util.isNetworkActive
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: DemoAppApi) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return if (isNetworkActive()) {
            try {
                Result.success(api.getCategories().data)
            } catch (ex: Exception) {
                Result.failure(ex)
            }
        } else {
            Result.failure(NetworkErrorException())
        }
    }
}