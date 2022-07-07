package com.example.swap.feature_advert.data

import com.example.swap.core.domain.models.Advert
import com.example.swap.core.util.Resource
import com.example.swap.core.util.SimpleResource
import com.example.swap.feature_advert.domain.repository.AdvertRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AdvertRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : AdvertRepository {

    private var operationSuccessful = false
    override fun getAllAdverts(userId: String): Resource<List<Advert>> {

    }

    override fun getAllAdvertsFromUser(userId: String): Resource<List<Advert>> {

    }

    override suspend fun getOneAdvert(advertId: String): Resource<Advert> {

    }

    override suspend fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ): SimpleResource {

    }

    override suspend fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ): SimpleResource {

    }

    override suspend fun deleteAdvert(advertId: String): SimpleResource {

    }

    /*override fun getAllAdverts(userId: String) *//*: Flow<Resource<List<Advert>>> = callbackFlow*//* {
        *//*val snapshotListener = firebaseFirestore.collection(ADVERT_COLLECTION)
            .whereNotEqualTo(ADVERT_DOCUMENT_AUTHOR_ID, userId)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val advertsList = snapshot.toObjects(Advert::class.java)
                    Resource.Success<List<Advert>>(advertsList)
                } else {
                    Resource.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }*//*
    }

    override fun getAllAdvertsFromUser(userId: String)*//*: Flow<Resource<List<Advert>>>*//* *//*=
        callbackFlow*//* {
           *//* val snapshotListener = firebaseFirestore.collection(ADVERT_COLLECTION)
                .whereEqualTo(ADVERT_DOCUMENT_AUTHOR_ID, userId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val advertsList = snapshot.toObjects(Advert::class.java)
                        Resource.Success<List<Advert>>(advertsList)
                    } else {
                        Resource.Error(e?.message ?: e.toString())
                    }
                    trySend(response).isSuccess
                }
            awaitClose {
                snapshotListener.remove()
            }*//*
        }

    override suspend fun getOneAdvert(advertId: String)*//*: Flow<Resource<Advert>> = callbackFlow*//* {
        *//*Resource.Loading<Advert>(true)
        val snapShotListener =
            firebaseFirestore.collection(USER_COLLECTION).document(advertId)
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {
                        val advertInfo = snapshot.toObject(Advert::class.java)
                        Resource.Success<Advert>(advertInfo!!)
                    } else {
                        Resource.Error(error?.localizedMessage ?: error.toString())
                    }
                    trySend(response).isSuccess
                }
        awaitClose {
            snapShotListener.remove()
        }*//*
    }

    override suspend fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    )*//*: Flow<Resource<Void?>> = flow *//*{
        *//*operationSuccessful = false
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
                val addition = firebaseFirestore.collection(ADVERT_COLLECTION).document(advertId)
                    .update(advertObj)
                    .addOnSuccessListener {
                        updated = true
                    }.await()
                if (updated)
                    emit(Resource.Success(addition))
                else {
                    emit(Resource.Error("Error creating ad! Try later."))
                }
            } else {
                emit(Resource.Error("Error creating ad! Try later."))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        }*//*
    }

    override suspend fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    )*//*: Flow<Resource<Void?>> = flow*//* {
        *//*operationSuccessful = false
        try {
            val advertObj = mutableMapOf<String, Any>()
            advertObj[ADVERT_DOCUMENT_TITLE] = title
            advertObj[ADVERT_DOCUMENT_DESCRIPTION] = description
            advertObj[ADVERT_DOCUMENT_IMAGE_LIST] = images
            advertObj[ADVERT_DOCUMENT_TAGS] = tags
            val update =
                firebaseFirestore.collection(ADVERT_COLLECTION).document(advertId).update(advertObj)
                    .await()
            emit(Resource.Success(update))
        } catch (e: Exception) {
            emit(Resource.Error(uiText = UiText.unknownError())
        }*//*
    }

    override suspend fun deleteAdvert(advertId: String)*//*: SimpleResource*//* {

    }*/
}

