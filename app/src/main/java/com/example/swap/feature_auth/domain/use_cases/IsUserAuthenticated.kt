package com.example.swap.feature_auth.domain.use_cases

import com.example.swap.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.isUserAuthenticated()
}