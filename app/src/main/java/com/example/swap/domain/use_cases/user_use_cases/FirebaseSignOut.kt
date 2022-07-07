package com.example.swap.domain.use_cases.user_use_cases

import com.example.swap.domain.repositories.UserRepository
import com.example.swap.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.firebaseLogOut()
}