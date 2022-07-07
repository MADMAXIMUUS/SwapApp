package com.example.swap.feature_auth.domain.models

import com.example.swap.core.util.SimpleResource
import com.example.swap.feature_auth.presentation.util.AuthError

data class SignInResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
