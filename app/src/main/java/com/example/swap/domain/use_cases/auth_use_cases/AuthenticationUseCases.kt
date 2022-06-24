package com.example.swap.domain.use_cases.auth_use_cases

data class AuthenticationUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthState,
    val firebaseLogIn: FirebaseLogIn,
    val firebaseLogOut: FirebaseLogOut,
    val firebaseSignInEmail: FirebaseSignInEmail
)