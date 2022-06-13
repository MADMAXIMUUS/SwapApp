package com.example.swap.domain.repositories

import com.example.swap.domain.models.User
import com.example.swap.objects.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserDetails(userId: String): Flow<Response<User>>
    fun setUserDetails(
        userId: String,
        name: String,
        email: String,
        phone: String
    ): Flow<Response<Boolean>>
}