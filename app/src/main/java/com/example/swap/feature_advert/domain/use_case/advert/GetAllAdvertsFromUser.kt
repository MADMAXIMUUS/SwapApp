package com.example.swap.feature_advert.domain.use_case.advert

import com.example.swap.feature_advert.domain.repository.AdvertRepository
import javax.inject.Inject

class GetAllAdvertsFromUser @Inject constructor(
    private val repository: AdvertRepository
) {
    operator fun invoke(userid:String)=repository.getAllAdvertsFromUser(userId = userid)
}