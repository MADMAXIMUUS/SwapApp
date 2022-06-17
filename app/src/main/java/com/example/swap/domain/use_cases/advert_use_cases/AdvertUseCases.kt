package com.example.swap.domain.use_cases.advert_use_cases

data class AdvertUseCases(
    val getAll: GetAllAdverts,
    val getOne: GetOneAdvert,
    val createAdvert: CreateAdvert,
    val updateAdvert: UpdateAdvert
)