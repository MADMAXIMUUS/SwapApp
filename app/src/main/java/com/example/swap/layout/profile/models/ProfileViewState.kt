package com.example.swap.layout.profile.models

import com.example.swap.core.domain.models.AdvertCardItem

sealed class ProfileViewState {
    object Loading : ProfileViewState()
    object Error : ProfileViewState()
    data class Display(
        val items: List<AdvertCardItem>,
        val name: String,
        val imageUrl: String,
        val rating: String,
        val id: String
    ) : ProfileViewState()
    object NoLogin : ProfileViewState()
}