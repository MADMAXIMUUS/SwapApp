package com.example.swap.feature_auth.data

import android.content.SharedPreferences
import com.example.swap.R
import com.example.swap.core.domain.models.User
import com.example.swap.core.util.Constants
import com.example.swap.core.util.Constants.SIGN_IN_TYPE_EMAIL
import com.example.swap.core.util.Constants.USER_COLLECTION
import com.example.swap.core.util.Resource
import com.example.swap.core.util.SimpleResource
import com.example.swap.core.util.UiText
import com.example.swap.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    private var operationSuccessful = false

    override suspend fun isUserAuthenticated(): SimpleResource {
        delay(1500L)
        return if (auth.currentUser != null)
            Resource.Success(Unit)
        else
            Resource.Error(UiText.unknownError())
    }

    override suspend fun firebaseSignIn(email: String, password: String): SimpleResource {
        operationSuccessful = false
        return try {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                sharedPreferences.edit()
                    .putString(Constants.KEY_USER_ID, auth.currentUser?.uid.toString())
                    .apply()
                operationSuccessful = true
            }.await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.localizedMessage ?: "An Unexpected Error"))
        }
    }

    override suspend fun firebaseSignUp(
        name: String,
        email: String,
        password: String
    ): SimpleResource {
        operationSuccessful = false
        return try {
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
                        sharedPreferences.edit()
                            .putString(Constants.KEY_USER_ID, userId)
                            .apply()
                    }.await()
                Resource.Success(Unit)
            } else {
                Resource.Error(UiText.StringResource(R.string.creating_error))
            }
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.localizedMessage ?: "An Unexpected Error"))
        }
    }
}