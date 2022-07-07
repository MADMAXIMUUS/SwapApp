package com.example.swap.feature_auth.domain.use_cases

import com.example.swap.core.domain.util.ValidationUtil
import com.example.swap.feature_auth.domain.models.SignUpResult
import com.example.swap.feature_auth.domain.repository.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ): SignUpResult {
        val emailError = ValidationUtil.validateEmail(email)
        val usernameError = ValidationUtil.validateUsername(name)
        val passwordError = ValidationUtil.validatePassword(password)

        if(emailError != null || usernameError != null || passwordError != null) {
            return SignUpResult(
                emailError = emailError,
                usernameError = usernameError,
                passwordError = passwordError,
            )
        }

        val result = repository.firebaseSignUp(name, email, password)

        return SignUpResult(
            result = result
        )
    }
}