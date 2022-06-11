package com.example.swap.domain.models

data class Advert(
    val id: String,
    val authorId: String,
    val title: String,
    val description: String,
    val mainImageUrl: String,
    val imageListUrls: List<String> = emptyList(),
    val tags: List<String> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        return (other as Advert).id == id
    }
}