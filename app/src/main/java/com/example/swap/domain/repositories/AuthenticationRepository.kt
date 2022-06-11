package com.example.swap.domain.repositories

import com.example.swap.utilities.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    fun getFirebaseAuthState(): Flow<Boolean>

    fun firebaseLogIn(email: String, password:String) : Flow<Response<Boolean>>

    fun firebaseLogOut(): Flow<Response<Boolean>>

    fun firebaseSignIn(name: String, email: String, password: String): Flow<Response<Boolean>>
}