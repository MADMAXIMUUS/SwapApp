package com.example.swap.domain.use_cases.user_use_cases

data class UserUseCases(
    val getUserDetails: GetUserDetails,
    val setUserDetails: SetUserDetails,
    val setUserName: SetUserName,
    val setUserEmail: SetUserEmail,
    val setUserPhone: SetUserPhone
)