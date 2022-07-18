package com.example.swap.feature_advert.domain

import com.example.swap.core.domain.models.Advert
import com.example.swap.core.util.Resource
import com.example.swap.core.util.SimpleResource

interface AdvertRepository {

    suspend fun getAdvertsFavorite(userId: String): Resource<List<Advert>>

    suspend fun getAllAdverts(userId: String, firstNumber: Long, limit: Long): Resource<List<Advert>>

    suspend fun getAdvertsFromUser(
        userId: String,
        firstNumber: Long,
        limit: Long
    ): Resource<List<Advert>>

    suspend fun getAdvertDetails(advertId: String): Resource<Advert>

    suspend fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ): SimpleResource

    suspend fun addAdvertToFavorite(advertId: String): SimpleResource

    suspend fun removeAdvertFromFavorite(advertId: String): SimpleResource

    suspend fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ): SimpleResource

    suspend fun deleteAdvert(advertId: String): SimpleResource
}