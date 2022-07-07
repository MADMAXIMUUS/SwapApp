package com.example.swap.di

import com.example.swap.feature_advert.data.AdvertRepositoryImpl
import com.example.swap.data.TagRepositoryImpl
import com.example.swap.feature_advert.domain.repository.AdvertRepository
import com.example.swap.domain.repositories.TagRepository
import com.example.swap.domain.use_cases.advert_use_cases.*
import com.example.swap.feature_advert.domain.use_case.tag.AddTag
import com.example.swap.feature_advert.domain.use_case.tag.GetAllTags
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
            GetAllAdverts(advertRepository),
            GetAllAdvertsFromUser(advertRepository),
            GetOneAdvert(advertRepository),
            CreateAdvert(advertRepository),
            UpdateAdvert(advertRepository)
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
            GetAllTags(tagRepository),
            AddTag(tagRepository)
        )
    }
}