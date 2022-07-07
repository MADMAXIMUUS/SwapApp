package com.example.swap.core.domain.use_case

import com.example.swap.feature_advert.domain.repository.AdvertRepository

class DeleteAdvertUseCase(
    private val repository: AdvertRepository
) {

    suspend operator fun invoke(advertId: String)/*: SimpleResource*/ {
        return repository.deleteAdvert(advertId)
    }
}