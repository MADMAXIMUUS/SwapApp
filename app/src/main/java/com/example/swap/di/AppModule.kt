package com.example.swap.di

import com.example.swap.data.AuthenticationRepositoryImpl
import com.example.swap.data.UserRepositoryImpl
import com.example.swap.domain.repositories.AuthenticationRepository
import com.example.swap.domain.repositories.UserRepository
import com.example.swap.domain.use_cases.auth_use_cases.*
import com.example.swap.domain.use_cases.user_use_cases.GetUserDetails
import com.example.swap.domain.use_cases.user_use_cases.SetUserDetails
import com.example.swap.domain.use_cases.user_use_cases.UserUserCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStore(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository) =
        AuthenticationUseCases(
            isUserAuthenticated = IsUserAuthenticated(repository),
            firebaseAuthState = FirebaseAuthState(repository),
            firebaseLogIn = FirebaseLogIn(repository),
            firebaseLogOut = FirebaseLogOut(repository),
            firebaseSignIn = FirebaseSignIn(repository)
        )

    @Singleton
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(userRepository: UserRepository) =
        UserUserCases(
            GetUserDetails(userRepository),
            SetUserDetails(userRepository)
        )
}