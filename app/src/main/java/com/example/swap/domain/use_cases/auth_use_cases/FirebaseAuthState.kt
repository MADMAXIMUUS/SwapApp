package com.example.swap.domain.use_cases.auth_use_cases

import com.example.swap.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}