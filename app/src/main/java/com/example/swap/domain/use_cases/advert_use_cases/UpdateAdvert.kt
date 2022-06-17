package com.example.swap.domain.use_cases.advert_use_cases

import com.example.swap.domain.repositories.AdvertRepository
import javax.inject.Inject

class UpdateAdvert @Inject constructor(
    private val repository: AdvertRepository
) {
    operator fun invoke(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ) = repository.updateAdvert(advertId, title, tags, images, description)
}