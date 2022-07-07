package com.example.swap.feature_auth.domain.repository

import com.example.swap.core.util.SimpleResource

interface AuthRepository {

    suspend fun isUserAuthenticated(): SimpleResource

    suspend fun firebaseSignIn(email: String, password: String): SimpleResource

    suspend fun firebaseSignUp(name: String, email: String, password: String): SimpleResource
}