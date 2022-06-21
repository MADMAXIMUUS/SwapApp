package com.example.swap.domain.repositories

import com.example.swap.domain.models.Advert
import com.example.swap.objects.Response
import kotlinx.coroutines.flow.Flow

interface AdvertRepository {

    fun getAllAdverts(userId: String): Flow<Response<List<Advert>>>

    fun getOneAdvert(advertId: String): Flow<Response<Advert>>

    fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ): Flow<Response<Boolean>>

    fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ): Flow<Response<Boolean>>
}