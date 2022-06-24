package com.example.swap.data

import com.example.swap.domain.models.User
import com.example.swap.domain.repositories.AuthenticationRepository
import com.example.swap.objects.Constants.SIGN_IN_TYPE_ANONYMOUS
import com.example.swap.objects.Constants.SIGN_IN_TYPE_EMAIL
import com.example.swap.objects.Constants.USER_COLLECTION
import com.example.swap.objects.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore

) : AuthenticationRepository {

    var operationSuccessful = false

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseLogIn(email: String, password: String): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            emit(Response.Success(operationSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }

    override fun firebaseLogOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }

    override fun firebaseSignInEmail(
        name: String,
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if (operationSuccessful) {
                val userId = auth.currentUser?.uid.toString()
                val obj = User(
                    id = userId,
                    signInType = SIGN_IN_TYPE_EMAIL,
                    name = name,
                    email = email
                )
                firestore.collection(USER_COLLECTION).document(userId).set(obj)
                    .addOnSuccessListener {

                    }.await()
                emit(Response.Success(operationSuccessful))
            } else {
                Response.Success(operationSuccessful)
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }
}