package com.example.swap.feature_auth.domain.use_cases

import com.example.swap.feature_auth.domain.models.SignInResult
import com.example.swap.feature_auth.domain.repository.AuthRepository
import com.example.swap.feature_auth.presentation.util.AuthError
import javax.inject.Inject

class SignInUseCase (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): SignInResult {
        val emailError = if(email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if(password.isBlank()) AuthError.FieldEmpty else null

        if(emailError != null || passwordError != null) {
            return SignInResult(emailError, passwordError)
        }

        return SignInResult(
            result = repository.firebaseSignIn(email, password)
        )
    }
}