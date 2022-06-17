package com.example.swap.domain.use_cases.advert_use_cases

import com.example.swap.domain.repositories.AdvertRepository
import javax.inject.Inject

class GetOneAdvert @Inject constructor(
    private val repository: AdvertRepository
) {
    operator fun invoke(advertId: String) = repository.getOneAdvert(advertId)
}