package com.example.swap.ui.layout.login.models

sealed class LoginEvent {
    object ActionClicked: LoginEvent()
    data class NameChanged(val value: String): LoginEvent()
    data class EmailChanged(val value: String): LoginEvent()
    data class PasswordChanged(val value: String): LoginEvent()
}
