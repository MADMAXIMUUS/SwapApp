package com.example.swap.domain.models

data class User(
    val id: String = "",
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var registrationDate: String = "",
    var avatarUrl: String = "empty",
    var rating: Double = 5.0,
    var drafts: List<String> = emptyList(),
    var favorites: List<String> = emptyList(),
    var adverts: List<String> = emptyList()
)
