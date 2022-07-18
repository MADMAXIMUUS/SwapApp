package com.example.swap.feature_advert.domain.use_case.advert

import com.example.swap.feature_advert.domain.AdvertRepository
import javax.inject.Inject

class GetAllAdvertsFromUser @Inject constructor(
    private val repository: AdvertRepository
) {
    suspend operator fun invoke(userid: String, firstNumber: Long, limit: Long) =
        repository.getAdvertsFromUser(userId = userid, firstNumber = firstNumber, limit = limit)
}