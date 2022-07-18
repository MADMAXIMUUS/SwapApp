package com.example.swap.feature_advert.domain.use_case.advert

import com.example.swap.feature_advert.domain.AdvertRepository
import javax.inject.Inject

class CreateAdvertUseCase @Inject constructor(
    private val repository: AdvertRepository
) {
    suspend operator fun invoke(
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