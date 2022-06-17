package com.example.swap.domain.models

data class Advert(
    val id: String = "",
    val authorId: String = "",
    val title: String = "",
    val description: String = "",
    val createdAt: Long? = null,
    val imageListUrls: List<String> = listOf("empty"),
    val tags: List<String> = emptyList()
)