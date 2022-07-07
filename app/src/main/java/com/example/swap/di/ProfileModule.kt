package com.example.swap.di

import com.example.swap.data.UserRepositoryImpl
import com.example.swap.domain.repositories.UserRepository
import com.example.swap.domain.use_cases.user_use_cases.GetUserDetails
import com.example.swap.domain.use_cases.user_use_cases.SetUserDetails
import com.example.swap.domain.use_cases.user_use_cases.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImpl(firestore, auth)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(userRepository: UserRepository): UserUseCases {
        return UserUseCases(
            GetUserDetails(userRepository),
            SetUserDetails(userRepository)
        )
    }
}