package com.example.swap.feature_advert.domain.use_case.advert

data class AdvertUseCases(
    val getAllWithUserId: GetAllAdvertsUseCase,
    val getAllAdvertsFromUser: GetAllAdvertsFromUser,
    val getAdvertDetails: GetAdvertDetailsUseCase,
    val createAdvertUseCase: CreateAdvertUseCase,
    val updateAdvertUseCase: UpdateAdvertUseCase,
    val deleteAdvertUseCase: DeleteAdvertUseCase,
    val addAdvertToFavoriteUseCase: AddAdvertToFavoriteUseCase,
    val removeAdvertFromFavoriteUseCase: RemoveAdvertFromFavoriteUseCase,
    val getAdvertsFavorite: GetAdvertsFavorite
)