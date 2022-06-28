package com.example.swap.ui.layout.login.models

enum class LoginSubState {
    SignIn, SignUp, Forgot
}

data class LoginViewState(
    val loginSubState: LoginSubState = LoginSubState.SignIn,
    val emailValue: String = "",
    val passwordValue: String = "",
    val nameValue: String = "",
    val isLoginProgress: Boolean = false
)
