package com.example.swap.domain.use_cases.advert_use_cases

import com.example.swap.domain.repositories.AdvertRepository
import javax.inject.Inject

class GetAllAdverts @Inject constructor(
    private val repository: AdvertRepository
) {
    operator fun invoke(userid:String)=repository.getAllAdverts(userId = userid)
}