package com.example.swap.domain.models

data class User(
    val id: String = "",
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var avatarUrl: String = "",
    var rating: Double = 0.0,
    var drafts: List<String> = emptyList(),
    var favorites: List<String> = emptyList()
)
