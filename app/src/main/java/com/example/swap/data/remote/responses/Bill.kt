package com.lopatasoftware.swap.data.remote.responses

data class Bill(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val title: String
)