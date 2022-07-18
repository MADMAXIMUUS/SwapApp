package com.example.swap.feature_advert.data

import com.example.swap.core.util.Resource
import com.example.swap.core.domain.models.Tag
import com.example.swap.domain.repositories.TagRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : TagRepository {

    private var operationSuccessful = false

    override fun getAllTags(): Flow<Resource<List<Tag>>> = callbackFlow {
        /*val snapshotListener = firebaseFirestore.collection(TAG_COLLECTION)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val tagList = snapshot.toObjects(Tag::class.java)
                    Response.Success<List<Tag>>(tagList)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }*/
    }

    override suspend fun addTag(text: String): Flow<Resource<Void?>> = flow {
        /*operationSuccessful = false
        try {
            val tagId = firebaseFirestore.collection(TAG_COLLECTION).document().id
            val tag = Tag(
                id = tagId,
                text = text
            )
            firebaseFirestore.collection(TAG_COLLECTION).document(tagId).set(tag)
                .addOnSuccessListener {
                    operationSuccessful = true
                }.await()
            if (operationSuccessful) {
                emit(Response.Success(operationSuccessful))
            } else {
                emit(Response.Error("Error creating ad! Try later."))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error"))
        }*/
    }
}