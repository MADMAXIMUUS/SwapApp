package com.example.swap.domain.repositories

import com.example.swap.domain.models.Tag
import com.example.swap.objects.Response
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getAllTags(): Flow<Response<List<Tag>>>
    fun addTag(text: String): Flow<Response<Boolean>>
}