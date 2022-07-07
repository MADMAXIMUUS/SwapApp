package com.example.swap.domain.repositories

import com.example.swap.core.util.Resource
import com.example.swap.core.domain.models.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getAllTags(): Flow<Resource<List<Tag>>>
    suspend fun addTag(text: String): Flow<Resource<Void?>>
}