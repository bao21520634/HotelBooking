package com.example.hotelbooking.di

import com.example.hotelbooking.data.repository.AuthRepositoryImpl
import com.example.hotelbooking.data.repository.HotelsRepositoryImpl
import com.example.hotelbooking.data.repository.UserRepositoryImpl
import com.example.hotelbooking.domain.repository.AuthRepository
import com.example.hotelbooking.domain.repository.HotelsRepository
import com.example.hotelbooking.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHotelsRepository(impl: HotelsRepositoryImpl) : HotelsRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl) : AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl) : UserRepository
}