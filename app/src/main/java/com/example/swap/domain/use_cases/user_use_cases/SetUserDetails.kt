package com.example.swap.domain.use_cases.user_use_cases

import com.example.swap.domain.repositories.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String, name: String, email: String, phone: String) =
        repository.setUserDetails(userId, name, email, phone)
}