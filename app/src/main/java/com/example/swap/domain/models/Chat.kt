package com.example.swap.domain.models

data class Chat(
    val id: String = "",
    val companionId: String = "",
    val unreadMessageCount: Int = 0,
    val lastMessage: String = "",
    val messages: List<Message> = emptyList()
)