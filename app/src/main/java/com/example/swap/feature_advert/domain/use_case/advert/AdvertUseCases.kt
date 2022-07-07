package com.example.swap.feature_advert.domain.use_case.advert

data class AdvertUseCases(
    val getAllWithUserId: GetAllAdvertsUseCase,
    val getAllAdvertsFromUser: GetAllAdvertsFromUser,
    val getOne: GetOneAdvertUseCase,
    val createAdvertUseCase: CreateAdvertUseCase,
    val updateAdvertUseCase: UpdateAdvertUseCase
)