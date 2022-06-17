package com.example.swap.domain.use_cases.user_use_cases

import com.example.swap.domain.repositories.UserRepository
import javax.inject.Inject

class SetUserName @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String, name: String) =
        repository.setUserName(userId, name)
}