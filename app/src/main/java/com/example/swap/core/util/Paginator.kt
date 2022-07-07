package com.example.swap.core.util

interface Paginator<T> {

    suspend fun loadNextItems()
}