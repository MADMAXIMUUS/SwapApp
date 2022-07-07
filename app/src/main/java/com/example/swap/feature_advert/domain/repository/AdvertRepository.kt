package com.example.swap.feature_advert.domain.repository

import com.example.swap.core.domain.models.Advert
import com.example.swap.core.util.Resource
import com.example.swap.core.util.SimpleResource

interface AdvertRepository {

    fun getAllAdverts(userId: String): Resource<List<Advert>>

    fun getAllAdvertsFromUser(userId: String): Resource<List<Advert>>

    suspend fun getOneAdvert(advertId: String): Resource<Advert>

    suspend fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ): SimpleResource

    suspend fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ): SimpleResource

    suspend fun deleteAdvert(advertId: String): SimpleResource
}