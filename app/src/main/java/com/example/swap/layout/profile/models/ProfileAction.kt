package com.example.swap.layout.profile.models

sealed class ProfileAction{
    data class OpenProfile(val userId: String="0") : ProfileAction()
    object OpenSignUpScreen : ProfileAction()
    object OpenForgotScreen : ProfileAction()
    object None: ProfileAction()
}