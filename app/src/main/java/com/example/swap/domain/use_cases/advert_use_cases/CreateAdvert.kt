package com.example.swap.domain.use_cases.advert_use_cases

import com.example.swap.domain.repositories.AdvertRepository
import javax.inject.Inject

class CreateAdvert @Inject constructor(
    private val repository: AdvertRepository
) {
    operator fun invoke(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ) = repository.createAdvert(
        authorId = authorId,
        tags = tags,
        title = title,
        images = images,
        description = description
    )
}