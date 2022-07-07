package com.example.swap.di

import android.content.SharedPreferences
import com.example.swap.feature_auth.data.AuthRepositoryImpl
import com.example.swap.feature_auth.domain.repository.AuthRepository
import com.example.swap.feature_auth.domain.use_cases.SignInUseCase
import com.example.swap.feature_auth.domain.use_cases.SignUpUseCase
import com.example.swap.feature_auth.domain.use_cases.IsUserAuthenticated
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(auth, firestore, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSingUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(repository: AuthRepository): SignInUseCase {
        return SignInUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(repository: AuthRepository): IsUserAuthenticated {
        return IsUserAuthenticated(repository)
    }
}