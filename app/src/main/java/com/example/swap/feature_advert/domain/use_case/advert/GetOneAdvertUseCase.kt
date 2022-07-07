package com.example.swap.feature_advert.domain.use_case.advert

import com.example.swap.feature_advert.domain.repository.AdvertRepository
import javax.inject.Inject

class GetOneAdvertUseCase @Inject constructor(
    private val repository: AdvertRepository
) {
    suspend operator fun invoke(advertId: String) = repository.getOneAdvert(advertId)
}