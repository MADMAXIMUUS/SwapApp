package com.example.swap.layout.profile.models

sealed class ProfileEvent  {
        data class EnterScreen(val userId: String) : ProfileEvent()
        object ReloadScreen : ProfileEvent()
        object SettingsClicked : ProfileEvent()
        data class OnAdvertClicked(val advertId: String) : ProfileEvent()
}