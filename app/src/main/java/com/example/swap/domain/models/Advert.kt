package com.example.swap.domain.models

import com.google.firebase.Timestamp

data class Advert(
    val id: String = "",
    val authorId: String = "",
    val title: String = "",
    val description: String = "",
    val createdAt: Timestamp? = null,
    val imageListUrls: List<String> = listOf("empty"),
    val tags: List<String> = emptyList()
)