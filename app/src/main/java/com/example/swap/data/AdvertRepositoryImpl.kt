package com.example.swap.data

import com.example.swap.domain.models.Advert
import com.example.swap.domain.repositories.AdvertRepository
import com.example.swap.objects.Constants
import com.example.swap.objects.Constants.ADVERT_COLLECTION
import com.example.swap.objects.Constants.ADVERT_DOCUMENT_AUTHOR_ID
import com.example.swap.objects.Constants.ADVERT_DOCUMENT_CREATED_AT
import com.example.swap.objects.Constants.ADVERT_DOCUMENT_DESCRIPTION
import com.example.swap.objects.Constants.ADVERT_DOCUMENT_IMAGE_LIST
import com.example.swap.objects.Constants.ADVERT_DOCUMENT_TAGS
import com.example.swap.objects.Constants.ADVERT_DOCUMENT_TITLE
import com.example.swap.objects.Response
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class AdvertRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : AdvertRepository {

    private var operationSuccessful = false

    override fun getAllAdverts(userId: String): Flow<Response<List<Advert>>> = callbackFlow {
        val snapshotListener = firebaseFirestore.collection(ADVERT_COLLECTION)
            .whereNotEqualTo(ADVERT_DOCUMENT_AUTHOR_ID, userId)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val advertsList = snapshot.toObjects(Advert::class.java)
                    Response.Success<List<Advert>>(advertsList)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getOneAdvert(advertId: String): Flow<Response<Advert>> = callbackFlow {
        Response.Loading
        val snapShotListener =
            firebaseFirestore.collection(Constants.USER_COLLECTION).document(advertId)
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {
                        val advertInfo = snapshot.toObject(Advert::class.java)
                        Response.Success<Advert>(advertInfo!!)
                    } else {
                        Response.Error(error?.localizedMessage ?: error.toString())
                    }
                    trySend(response).isSuccess
                }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            val advertId = firebaseFirestore.collection(ADVERT_COLLECTION).document().id
            val advert = Advert(
                title = title,
                description = description,
                id = advertId,
                tags = tags,
                imageListUrls = images,
                authorId = authorId
            )
            firebaseFirestore.collection(ADVERT_COLLECTION).document(advertId).set(advert)
                .addOnSuccessListener {
                    operationSuccessful = true
                }.await()
            if (operationSuccessful) {
                var updated = false
                val advertObj = mutableMapOf<String, Any>()
                advertObj[ADVERT_DOCUMENT_CREATED_AT] = FieldValue.serverTimestamp()
                firebaseFirestore.collection(ADVERT_COLLECTION).document(advertId)
                    .update(advertObj)
                    .addOnSuccessListener {
                        updated = true
                    }.await()
                if (updated)
                    emit(Response.Success(operationSuccessful))
                else {
                    emit(Response.Error("Error creating ad! Try later."))
                }
            } else {
                emit(Response.Error("Error creating ad! Try later."))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error"))
        }
    }

    override fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            val advertObj = mutableMapOf<String, Any>()
            advertObj[ADVERT_DOCUMENT_TITLE] = title
            advertObj[ADVERT_DOCUMENT_DESCRIPTION] = description
            advertObj[ADVERT_DOCUMENT_IMAGE_LIST] = images
            advertObj[ADVERT_DOCUMENT_TAGS] = tags
            firebaseFirestore.collection(ADVERT_COLLECTION).document(advertId).update(advertObj)
                .addOnSuccessListener {

                }.await()
            if (operationSuccessful) {
                emit(Response.Success(operationSuccessful))
            } else {
                emit(Response.Error("Error when saving changes"))
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An Unexpected Error")
        }
    }
}

