package com.example.swap.core.domain.models


data class Message(
    val id: String = "",
    val from: String = "",
    val type: String = "",
    var text: String = "",
    var attachmentsName: String = "",
    val attachmentsUrls: List<String> = emptyList(),
    val timestamp: Long = 0
)
