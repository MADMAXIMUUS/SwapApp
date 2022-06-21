package com.example.swap.di

import com.example.swap.data.AdvertRepositoryImpl
import com.example.swap.data.AuthenticationRepositoryImpl
import com.example.swap.data.TagRepositoryImpl
import com.example.swap.data.UserRepositoryImpl
import com.example.swap.domain.repositories.AdvertRepository
import com.example.swap.domain.repositories.AuthenticationRepository
import com.example.swap.domain.repositories.TagRepository
import com.example.swap.domain.repositories.UserRepository
import com.example.swap.domain.use_cases.advert_use_cases.*
import com.example.swap.domain.use_cases.auth_use_cases.*
import com.example.swap.domain.use_cases.tag_use_cases.AddTag
import com.example.swap.domain.use_cases.tag_use_cases.GetAllTags
import com.example.swap.domain.use_cases.tag_use_cases.TagUseCases
import com.example.swap.domain.use_cases.user_use_cases.*
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
            firebaseSignInEmail = FirebaseSignInEmail(repository),
            firebaseSignInAnon = FirebaseSignInAnon(repository)
        )

    @Singleton
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(userRepository: UserRepository) =
        UserUseCases(
            GetUserDetails(userRepository),
            SetUserDetails(userRepository),
            SetUserName(userRepository),
            SetUserEmail(userRepository),
            SetUserPhone(userRepository)
        )


    @Singleton
    @Provides
    fun provideAdvertRepository(firestore: FirebaseFirestore): AdvertRepository {
        return AdvertRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideAdvertUseCases(advertRepository: AdvertRepository) =
        AdvertUseCases(
            GetAllAdvertsUserId(advertRepository),
            GetOneAdvert(advertRepository),
            CreateAdvert(advertRepository),
            UpdateAdvert(advertRepository)
        )

    @Singleton
    @Provides
    fun provideTagRepository(firestore: FirebaseFirestore): TagRepository {
        return TagRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideTagUseCases(tagRepository: TagRepository) =
        TagUseCases(
            GetAllTags(tagRepository),
            AddTag(tagRepository)
        )
}