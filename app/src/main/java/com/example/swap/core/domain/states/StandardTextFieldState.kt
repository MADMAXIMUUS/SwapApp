package com.example.swap.core.domain.states

import com.example.swap.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
