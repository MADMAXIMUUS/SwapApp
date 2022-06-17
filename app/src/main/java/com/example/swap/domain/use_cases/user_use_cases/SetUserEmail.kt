package com.example.swap.domain.use_cases.user_use_cases

import com.example.swap.domain.repositories.UserRepository
import javax.inject.Inject

class SetUserEmail @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String, email: String) =
        repository.setUserEmail(userId, email)
}