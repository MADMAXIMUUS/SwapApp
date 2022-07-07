package com.example.swap.data

import com.example.swap.core.domain.models.User
import com.example.swap.core.util.Resource
import com.example.swap.domain.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserRepository {

    override suspend fun getUserDetails(userId: String): Flow<Resource<User>> = callbackFlow {
        /*Resource.Loading<User>(true)
        val snapShotListener =
            firebaseFirestore.collection(USER_COLLECTION).document(userId)
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {
                        val userInfo = snapshot.toObject(User::class.java)
                        Resource.Success(userInfo)
                    } else {
                        Resource.Error(error?.localizedMessage ?: error.toString())
                    }
                    trySend(response).isSuccess
                }
        awaitClose {
            snapShotListener.remove()
        }*/
    }

    override suspend fun firebaseLogOut(): Flow<Resource<Boolean>> = flow {
        /*emit(Resource.Loading(true))
        try {
            auth.signOut()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error"))
        }*/
    }

    override suspend fun setUserDetails(
        userId: String,
        name: String,
        email: String,
        phone: String
    ): Flow<Resource<Void?>> = flow {
        /*try {
            val userObj = mutableMapOf<String, Any>()
            userObj[USER_DOCUMENT_NAME] = name
            userObj[USER_DOCUMENT_EMAIL] = email
            userObj[USER_DOCUMENT_PHONE] = phone
            val addition =
                firebaseFirestore.collection(USER_COLLECTION).document(userId).update(userObj)
                    .await()
            emit(Resource.Success(addition))
        } catch (e: Exception) {
            Resource.Error<Void?>(e.localizedMessage ?: "An Unexpected Error")
        }*/
    }
}