package com.example.swap.feature_advert.domain.use_case.advert

import com.example.swap.feature_advert.domain.AdvertRepository
import javax.inject.Inject

class AddAdvertToFavoriteUseCase @Inject constructor(
    private val repository: AdvertRepository
) {
    suspend fun invoke(advertId: String) = repository.addAdvertToFavorite(advertId)
}