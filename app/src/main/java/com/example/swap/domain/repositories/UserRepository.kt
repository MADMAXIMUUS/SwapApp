package com.example.swap.domain.repositories

import com.example.swap.core.domain.models.User
import com.example.swap.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserDetails(userId: String): Flow<Resource<User>>

    suspend fun firebaseLogOut(): Flow<Resource<Boolean>>

    suspend fun setUserDetails(
        userId: String,
        name: String,
        email: String,
        phone: String
    ): Flow<Resource<Void?>>
}