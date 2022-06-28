package com.example.swap.common

interface EventHandler<E> {
    fun obtainEvent(event: E)
}