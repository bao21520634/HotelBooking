package com.example.hotelbooking.di

import com.example.hotelbooking.data.repository.HotelsRepositoryImpl
import com.example.hotelbooking.domain.repository.HotelsRepository
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
}