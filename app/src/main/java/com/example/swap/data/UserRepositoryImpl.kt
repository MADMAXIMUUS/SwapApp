package com.example.swap.data

import com.example.swap.domain.models.User
import com.example.swap.domain.repositories.UserRepository
import com.example.swap.objects.Constants.USER_COLLECTION
import com.example.swap.objects.Constants.USER_DOCUMENT_EMAIL
import com.example.swap.objects.Constants.USER_DOCUMENT_NAME
import com.example.swap.objects.Constants.USER_DOCUMENT_PHONE
import com.example.swap.objects.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    private var operationSuccessful = false

    override fun getUserDetails(userId: String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapShotListener =
            firebaseFirestore.collection(USER_COLLECTION).document(userId)
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {
                        val userInfo = snapshot.toObject(User::class.java)
                        Response.Success(userInfo!!)
                    } else {
                        Response.Error(error?.localizedMessage ?: error.toString())
                    }
                    trySend(response).isSuccess
                }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun setUserDetails(
        userId: String,
        name: String,
        email: String,
        phone: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String, Any>()
            userObj[USER_DOCUMENT_NAME] = name
            userObj[USER_DOCUMENT_EMAIL] = email
            userObj[USER_DOCUMENT_PHONE] = phone
            firebaseFirestore.collection(USER_COLLECTION).document(userId).update(userObj)
                .addOnSuccessListener {

                }.await()
            if (operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }else{
                emit(Response.Error("Error when saving changes"))
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An Unexpected Error")
        }
    }

    override fun setUserName(userId: String, userName: String): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String, Any>()
            userObj[USER_DOCUMENT_NAME] = userName
            firebaseFirestore.collection(USER_COLLECTION).document(userId).update(userObj)
                .addOnSuccessListener {

                }.await()
            if (operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }else{
                emit(Response.Error("Error when saving changes"))
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An Unexpected Error")
        }
    }

    override fun setUserEmail(userId: String, email: String): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String, Any>()
            userObj[USER_DOCUMENT_EMAIL] = email
            firebaseFirestore.collection(USER_COLLECTION).document(userId).update(userObj)
                .addOnSuccessListener {

                }.await()
            if (operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }else{
                emit(Response.Error("Error when saving changes"))
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An Unexpected Error")
        }
    }

    override fun setUserPhone(userId: String, phone: String): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String, Any>()
            userObj[USER_DOCUMENT_PHONE] = phone
            firebaseFirestore.collection(USER_COLLECTION).document(userId).update(userObj)
                .addOnSuccessListener {

                }.await()
            if (operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }else{
                emit(Response.Error("Error when saving changes"))
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An Unexpected Error")
        }
    }
}