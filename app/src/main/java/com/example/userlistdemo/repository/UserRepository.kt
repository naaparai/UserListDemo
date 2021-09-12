package com.example.userlistdemo.repository

import com.example.userlistdemo.model.User
import com.example.userlistdemo.util.Resource

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}