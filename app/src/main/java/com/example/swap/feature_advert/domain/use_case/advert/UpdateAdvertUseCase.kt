package com.example.swap.feature_advert.domain.use_case.advert

import com.example.swap.feature_advert.domain.AdvertRepository
import javax.inject.Inject

class UpdateAdvertUseCase @Inject constructor(
    private val repository: AdvertRepository
) {
    suspend operator fun invoke(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ) = repository.updateAdvert(advertId, title, tags, images, description)
}