package com.example.swap.data

import com.example.swap.domain.repositories.AuthenticationRepository
import com.example.swap.utilities.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImplementation @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore

) : AuthenticationRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> {

    }

    override fun firebaseLogIn(email: String, password: String): Flow<Response<Boolean>> {

    }

    override fun firebaseLogOut(): Flow<Response<Boolean>> {

    }

    override fun firebaseSignIn(
        name: String,
        email: String,
        password: String
    ): Flow<Response<Boolean>> {

    }
}