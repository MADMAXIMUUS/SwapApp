package com.example.swap.feature_auth.presentation.signin

sealed class SignInEvent {
    data class EnteredEmail(val email: String): SignInEvent()
    data class EnteredPassword(val password: String): SignInEvent()
    object SignIn: SignInEvent()
    object TogglePasswordVisibility: SignInEvent()
}
