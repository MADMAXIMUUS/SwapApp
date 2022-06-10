package com.example.swap.models

data class ChatModel(
    val id:String ="",
    val from:String="",
    val unReadMessage:Int=0,
    val lastMessage: String=""
)