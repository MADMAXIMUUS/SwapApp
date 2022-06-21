package com.example.swap.domain.use_cases.auth_use_cases

import com.example.swap.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignInEmail @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(name: String, email: String, password: String) =
        repository.firebaseSignInEmail(name, email, password)
}