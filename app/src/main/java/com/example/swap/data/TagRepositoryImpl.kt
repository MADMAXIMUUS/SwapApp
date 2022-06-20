package com.example.swap.data

import com.example.swap.domain.models.Tag
import com.example.swap.domain.repositories.TagRepository
import com.example.swap.objects.Constants
import com.example.swap.objects.Constants.TAG_COLLECTION
import com.example.swap.objects.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : TagRepository {

    private var operationSuccessful = false

    override fun getAllTags(): Flow<Response<List<Tag>>> = callbackFlow {
        val snapshotListener = firebaseFirestore.collection(TAG_COLLECTION)
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
        }
    }

    override fun addTag(text: String): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
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
        }
    }
}