package com.example.swap.di

import com.example.swap.feature_advert.data.AdvertRepositoryImpl
import com.example.swap.feature_advert.data.repository.TagRepositoryImpl
import com.example.swap.feature_advert.domain.repository.AdvertRepository
import com.example.swap.domain.repositories.TagRepository
import com.example.swap.feature_advert.domain.use_case.advert.*
import com.example.swap.feature_advert.domain.use_case.tag.AddTagUseCase
import com.example.swap.feature_advert.domain.use_case.tag.GetAllTagsUseCase
import com.example.swap.feature_advert.domain.use_case.tag.TagUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdvertModule {

    @Singleton
    @Provides
    fun provideAdvertRepository(firestore: FirebaseFirestore): AdvertRepository {
        return AdvertRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideAdvertUseCases(advertRepository: AdvertRepository): AdvertUseCases {
        return AdvertUseCases(
            GetAllAdvertsUseCase(advertRepository),
            GetAllAdvertsFromUser(advertRepository),
            GetAdvertDetailsUseCase(advertRepository),
            CreateAdvertUseCase(advertRepository),
            UpdateAdvertUseCase(advertRepository),
            DeleteAdvertUseCase(advertRepository),
            AddAdvertToFavoriteUseCase(advertRepository),
            RemoveAdvertFromFavoriteUseCase(advertRepository),
            GetAdvertsFavorite(advertRepository)
        )
    }

    @Singleton
    @Provides
    fun provideTagRepository(firestore: FirebaseFirestore): TagRepository {
        return TagRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideTagUseCases(tagRepository: TagRepository): TagUseCases {
        return TagUseCases(
            GetAllTagsUseCase(tagRepository),
            AddTagUseCase(tagRepository)
        )
    }
}